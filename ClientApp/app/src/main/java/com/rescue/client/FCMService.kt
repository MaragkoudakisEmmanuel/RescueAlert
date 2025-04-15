/*
RescueClient - Android Kotlin App
Λειτουργίες:
- LoginActivity: Εγγραφή και Σύνδεση χρηστών με Firebase
- MainActivity: Ειδοποίηση με ήχο, φόντο και απάντηση χρήστη
- FCMService: Διαχείριση Firebase Cloud Messaging
- FirebaseUtils: Αποθήκευση απαντήσεων και logs
*/
// FCMService code placeholder
package com.rescue.client

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val message = remoteMessage.data["message"] ?: ""
        val urgency = remoteMessage.data["urgency"] ?: "normal"

        val intent = MainActivity() // replace with broadcast or start activity logic if needed
        intent.handleNotification(message, urgency)
    }
}

// FirebaseUtils.kt
package com.rescue.client

import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

object FirebaseUtils {
    fun saveResponse(userId: String, response: String) {
        val database = FirebaseDatabase.getInstance().reference
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        val responseData = mapOf(
            "status" to response,
            "timestamp" to timestamp
        )
        database.child("responses").child(userId).setValue(responseData)
        database.child("logs").child(userId).push().setValue(responseData)
    }
}
