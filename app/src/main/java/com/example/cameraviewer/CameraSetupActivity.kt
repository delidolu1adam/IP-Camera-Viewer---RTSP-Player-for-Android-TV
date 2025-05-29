package com.example.cameraviewer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CameraSetupActivity : AppCompatActivity() {

    private var slotIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_setup)

        slotIndex = intent.getIntExtra("slotIndex", 0)

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val ipInput = findViewById<EditText>(R.id.ipInput)
        val portInput = findViewById<EditText>(R.id.portInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Kaydedilmiş kamera bilgilerini yükle
        val camera = CameraPrefs.loadCamera(this, slotIndex)
        usernameInput.setText(camera.username)
        passwordInput.setText(camera.password)
        ipInput.setText(camera.ip)
        portInput.setText(camera.port)

        saveButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val ip = ipInput.text.toString()
            val port = portInput.text.toString()

            if (ip.isBlank()) {
                Toast.makeText(this, "IP adresi boş olamaz", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CameraPrefs.saveCamera(this, slotIndex, username, password, ip, port)
            Toast.makeText(this, "Kamera kaydedildi", Toast.LENGTH_SHORT).show()

            // Tam ekran oynatıcıya geç
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("slotIndex", slotIndex)
            startActivity(intent)
            finish()
        }
    }
}
