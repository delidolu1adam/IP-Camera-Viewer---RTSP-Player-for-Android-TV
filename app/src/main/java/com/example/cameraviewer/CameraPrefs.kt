package com.example.cameraviewer

import android.content.Context

object CameraPrefs {

    fun saveCamera(context: Context, index: Int, username: String, password: String, ip: String, port: String) {
        val prefs = context.getSharedPreferences("CameraPrefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("username_$index", username)
            putString("password_$index", password)
            putString("ip_$index", ip)
            putString("port_$index", port)
            apply()
        }
    }

    fun loadCamera(context: Context, index: Int): CameraInfo {
        val prefs = context.getSharedPreferences("CameraPrefs", Context.MODE_PRIVATE)
        return CameraInfo(
            prefs.getString("username_$index", "") ?: "",
            prefs.getString("password_$index", "") ?: "",
            prefs.getString("ip_$index", "") ?: "",
            prefs.getString("port_$index", "554") ?: "554"
        )
    }

    data class CameraInfo(
        val username: String,
        val password: String,
        val ip: String,
        val port: String
    )
}
