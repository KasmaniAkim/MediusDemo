package com.demo.medius

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import android.widget.EditText
import java.util.regex.Pattern

class SMSReceiver : BroadcastReceiver() {
    fun setEditText(editText: EditText?) {
        Companion.editText = editText
    }

    override fun onReceive(context: Context, intent: Intent) {
        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        for (sms in messages) {
            val message = sms.messageBody
            Log.d("Message", message.toString())
            editText!!.setText(extractDigits(message))
        }
    }

    companion object {
        private var editText: EditText? = null
        fun extractDigits(`in`: String?): String {
            val p = Pattern.compile("(\\d{6})")
            val m = p.matcher(`in`)
            return if (m.find()) {
                m.group(0).toString()
            } else ""
        }
    }
}