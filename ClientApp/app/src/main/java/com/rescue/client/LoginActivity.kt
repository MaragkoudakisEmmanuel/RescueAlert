/*
RescueClient - Android Kotlin App
Λειτουργίες:
- LoginActivity: Εγγραφή και Σύνδεση χρηστών με Firebase
- MainActivity: Ειδοποίηση με ήχο, φόντο και απάντηση χρήστη
- FCMService: Διαχείριση Firebase Cloud Messaging
- FirebaseUtils: Αποθήκευση απαντήσεων και logs
*/

// LoginActivity code placeholder
package com.rescue.client

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) startActivity(Intent(this, MainActivity::class.java))
                    else Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
        }

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) startActivity(Intent(this, MainActivity::class.java))
                    else Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
