package com.example.cameraviewer

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerView = PlayerView(this).apply {
            layoutParams = android.widget.FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            useController = false
        }
        setContentView(playerView)

        val slotIndex = intent.getIntExtra("slotIndex", 0)
        val prefs = getSharedPreferences("CameraPrefs", Context.MODE_PRIVATE)
        val username = prefs.getString("username_$slotIndex", "") ?: ""
        val password = prefs.getString("password_$slotIndex", "") ?: ""
        val ip = prefs.getString("ip_$slotIndex", "") ?: ""
        val port = prefs.getString("port_$slotIndex", "554") ?: "554"

        val rtspUrl = "rtsp://$username:$password@$ip:$port"

        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem = MediaItem.fromUri(Uri.parse(rtspUrl))
        player.setMediaItem(mediaItem)
