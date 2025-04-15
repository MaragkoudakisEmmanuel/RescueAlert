/*
RescueClient - Android Kotlin App
Λειτουργίες:
- LoginActivity: Εγγραφή και Σύνδεση χρηστών με Firebase
- MainActivity: Ειδοποίηση με ήχο, φόντο και απάντηση χρήστη
- FCMService: Διαχείριση Firebase Cloud Messaging
- FirebaseUtils: Αποθήκευση απαντήσεων και logs
*/

// MainActivity code placeholder
package com.rescue.client

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnImmediate).setOnClickListener { respond("ʼμεσα") }
        findViewById<Button>(R.id.btnLater).setOnClickListener { respond("Αργότερα") }
        findViewById<Button>(R.id.btnCant).setOnClickListener { respond("Δεν μπορώ") }
    }

    fun handleNotification(message: String, urgency: String) {
        val background = findViewById<View>(R.id.backgroundView)
        val duration = if (urgency == "urgent") 20000L else 10000L
        val color = if (urgency == "urgent") R.color.orange else R.color.red

        background.setBackgroundResource(color)
        mediaPlayer = MediaPlayer.create(this, R.raw.alert_sound)
        mediaPlayer.start()
        background.postDelayed({ mediaPlayer.stop() }, duration)
    }

    private fun respond(answer: String) {
        FirebaseUtils.saveResponse(userId, answer)
        // Αλλαγή χρώματος οθόνης ανάλογα με απάντηση
        val background = findViewById<View>(R.id.backgroundView)
        when (answer) {
            "ʼμεσα" -> background.setBackgroundResource(R.color.yellow)
            "Αργότερα" -> background.setBackgroundResource(R.color.yellow)
            else -> background.setBackgroundResource(R.color.green)
        }
    }
}