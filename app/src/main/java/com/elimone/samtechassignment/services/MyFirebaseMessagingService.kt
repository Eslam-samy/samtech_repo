package com.elimone.samtechassignment.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.elimone.samtechassignment.MainActivity
import com.elimone.samtechassignment.R
import com.elimone.samtechassignment.core.GroupId
import com.elimone.samtechassignment.core.channelGroup
import com.elimone.samtechassignment.core.channelId
import com.elimone.samtechassignment.featurs.home.data.data_source.NotificationsDao
import com.elimone.samtechassignment.featurs.home.data.model.NotificationLocalModel
import com.elimone.samtechassignment.utils.PrefKeys
import com.elimone.samtechassignment.utils.PrefUtils
import com.elimone.samtechassignment.utils.getCurrentLocalDateTimeAsString
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {


    // inject the dao to services class
    @Inject
    lateinit var notificationsDao: NotificationsDao

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)


    private val TAG = "MyFirebaseMessagingServ"

    // Called when a new FCM token is generated or refreshed
    override fun onNewToken(token: String) {
        // Save the new token to shared preferences
        PrefUtils.saveToPrefs(this, PrefKeys.FIRE_BASE_TOKEN, token)
        Log.d(TAG, "onNewToken: $token")
        super.onNewToken(token)
    }


    // Handle incoming FCM messages
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Log the sender's information
        Log.d("MyFirebaseMessagingServ12", "(nested Bundle)")
        val data = remoteMessage.data
        saveNotificationLocally(data)
        createNotification(
            data["title"],
            data["body"]
        )

    }

    private fun createNotification(title: String?, body: String?) {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(body)
            .setGroup(channelGroup)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(createPendingIntent())

        val summaryNotification = NotificationCompat.Builder(this, channelId)
            .setContentText("Summary Content")
            .setSmallIcon(R.drawable.logo)
            // Build summary info into InboxStyle template.
            .setStyle(
                NotificationCompat.InboxStyle()
                    .setSummaryText("New Notification")
            )
            .setAutoCancel(true)
            // Specify which group this notification belongs to.
            .setGroup(channelGroup)
            // Set this notification as the summary for the group.
            .setGroupSummary(true)


        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(createID(), builder.build())
        notificationManager.notify(GroupId, summaryNotification.build())
    }

    private fun createPendingIntent(): PendingIntent? {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("animation", "true")  // Add your extras here
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val name = "AssignmentNotification"
            val descriptionText = "Channel for Assignment App notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun createID(): Int {
        return SimpleDateFormat("ddHHmmss", Locale.US).format(Date()).toInt()
    }


    private fun saveNotificationLocally(data: Map<String, String>) {
        val notification = NotificationLocalModel(
            id = UUID.randomUUID().toString(),
            title = data["title"],
            body = data["body"],
            time = getCurrentLocalDateTimeAsString()
        )
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                notificationsDao.insertNotification(notification)
            }
        }

    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}
