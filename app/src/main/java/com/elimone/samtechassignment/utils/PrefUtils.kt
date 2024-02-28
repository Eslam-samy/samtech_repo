package com.elimone.samtechassignment.utils
import android.content.Context
import java.lang.ref.WeakReference

class PrefUtils {

    /**
     * Called to save supplied value in shared preferences against given key.
     *
     * @param context Context of caller activity
     * @param key     Key of value to save against
     * @param value   Value to save
     */
    companion object {
        fun saveToPrefs(context: Context?, key: String?, value: Any?) {
            val contextWeakReference = WeakReference(context)
            if (contextWeakReference.get() != null) {
                val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    contextWeakReference.get()!!
                )
                val editor = prefs.edit()
                when (value) {
                    is Int -> {
                        editor.putInt(key, value.toInt())
                    }
                    is String -> {
                        editor.putString(key, value.toString())
                    }
                    is Boolean -> {
                        editor.putBoolean(key, value.toString().toBoolean())
                    }
                    is Long -> {
                        editor.putLong(key, value.toLong())
                    }
                    is Float -> {
                        editor.putFloat(key, value.toFloat())
                    }
                    is Double -> {
                        editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
                    }
                }
                editor.apply()
            }
        }


        /**
         * Called to retrieve required value from shared preferences, identified by given key.
         * Default value will be returned of no value found or error occurred.
         *
         * @param context      Context of caller activity
         * @param key          Key to find value against
         * @param defaultValue Value to return if no data found against given key
         * @return Return the value found against given key, default if not found or any error occurs
         */
        fun getFromPrefs(context: Context?, key: String?, defaultValue: Any): Any? {
            val contextWeakReference = WeakReference(context)
            if (contextWeakReference.get() != null) {

                val sharedPrefs =
                    androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                        contextWeakReference.get()!!
                    )
                try {
                    when (defaultValue) {
                        is String -> {
                            return sharedPrefs.getString(key, defaultValue.toString())
                        }
                        is Int -> {
                            return sharedPrefs.getInt(key, defaultValue.toInt())
                        }
                        is Boolean -> {
                            return sharedPrefs.getBoolean(key, defaultValue.toString().toBoolean())
                        }
                        is Long -> {
                            return sharedPrefs.getLong(key, defaultValue.toLong())
                        }
                        is Float -> {
                            return sharedPrefs.getFloat(key, defaultValue.toFloat())
                        }
                        is Double -> {
                            return java.lang.Double.longBitsToDouble(
                                sharedPrefs.getLong(
                                    key, java.lang.Double.doubleToLongBits(
                                        defaultValue
                                    )
                                )
                            )
                        }
                    }
                } catch (e: Exception) {

                    return defaultValue
                }
            }

            return defaultValue
        }

        /**
         * @param context Context of caller activity
         * @param key     Key to delete from SharedPreferences
         */
        fun removeFromPrefs(context: Context?, key: String?) {
            val contextWeakReference = WeakReference(context)
            if (contextWeakReference.get() != null) {
                val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    contextWeakReference.get()!!
                )
                val editor = prefs.edit()
                editor.remove(key)
                editor.apply()
            }
        }

        fun hasKey(context: Context?, key: String?): Boolean {
            val contextWeakReference = WeakReference(context)
            if (contextWeakReference.get() != null) {
                val prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
                    contextWeakReference.get()!!
                )
                return prefs.contains(key)
            }
            return false
        }
    }
}