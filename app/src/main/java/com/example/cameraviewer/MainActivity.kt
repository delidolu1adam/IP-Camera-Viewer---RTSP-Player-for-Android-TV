package com.example.cameraviewer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridLayout = findViewById<GridLayout>(R.id.cameraGrid)
        val slotCount = 8

        for (i in 0 until slotCount) {
            val button = Button(this).apply {
                text = "Kamera ${i + 1}"
                textSize = 18f
                isFocusable = true
                setOnClickListener {
                    val intent = Intent(this@MainActivity, CameraSetupActivity::class.java)
                    intent.putExtra("slotIndex", i)
                    startActivity(intent)
                }
            }
            gridLayout.addView(button)
        }
    }
}
