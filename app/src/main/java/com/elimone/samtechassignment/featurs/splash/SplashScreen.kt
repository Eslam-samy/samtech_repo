package com.elimone.samtechassignment.featurs.splash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.elimone.samtechassignment.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        checkForState()
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)

    }

    private fun checkForState() {
        lifecycleScope.launch {
            delay(2000)
            val bundle = requireActivity().intent.extras
            printBundleData(bundle)
            if (bundle?.getString("animation") != null) {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToAnimatedFragment())
            } else {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToHomeFragment())

            }
        }
        // if the App is in background state then we  check for the extras of the notification

    }

    private fun printBundleData(bundle: Bundle?, indent: String = "") {
        bundle?.let {
            for (key in it.keySet()) {
                val value = it.get(key)
                if (value is Bundle) {
                    Log.d("MyFirebaseMessagingServ12", "$indent$key: (nested Bundle)")
                    printBundleData(value, "$indent\t") // Add additional indentation
                } else {
                    Log.d("MyFirebaseMessagingServ12", "$indent$key: $value")
                }
            }
        }
    }

}