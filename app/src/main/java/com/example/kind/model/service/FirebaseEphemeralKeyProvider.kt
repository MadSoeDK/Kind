package com.example.kind.model.service

import android.util.Log
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.stripe.android.EphemeralKeyProvider
import com.stripe.android.EphemeralKeyUpdateListener


/*
 * @author thorsten-stripe
 * https://github.com/stripe-archive/firebase-mobile-payments/blob/main/android/app/src/main/java/com/example/firebasemobilepayments/FirebaseEphemeralKeyProvider.kt
 * Client that triggers the firebase function that implements the Stripe API.
 */
class FirebaseEphemeralKeyProvider : EphemeralKeyProvider {
    override fun createEphemeralKey(
        apiVersion: String,
        keyUpdateListener: EphemeralKeyUpdateListener
    ) {
        val data = hashMapOf(
            "api_version" to apiVersion,
            "customer_id" to "cus_NATNzQbvJN2rh8"
        )

        val functions = FirebaseFunctions.getInstance()
        //functions.useEmulator("10.0.2.2", 5001)

        functions.getHttpsCallable("createEphemeralKey")
            .call(data)
            .continueWith { task ->
                if (!task.isSuccessful) {
                    val e = task.exception
                    if (e is FirebaseFunctionsException) {
                        Log.e("EphemeralKey", "Ephemeral key provider returns error: ${e.printStackTrace()}")
                    }
                }
                val key = task.result?.data.toString()
                Log.d("EphemeralKey", "Ephemeral key provider returns $key")
                keyUpdateListener.onKeyUpdate(key)
            }
    }
}