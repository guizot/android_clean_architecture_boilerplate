package com.guizot.android_clean_architecture_boilerplate.core.data
import okhttp3.logging.HttpLoggingInterceptor
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

class PrettyLogger(private val tag: String = "Network") : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        // Sometimes OkHttp splits the message, so we accumulate lines or check format
        if (message.trim().startsWith("{") || message.trim().startsWith("[")) {
            try {
                val jsonElement = JsonParser.parseString(message)
                val prettyJson = Gson().newBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(jsonElement)
                Log.d(tag, prettyJson)
            } catch (e: JsonSyntaxException) {
                Log.d(tag, message)  // fallback if not valid JSON
            }
        } else {
            Log.d(tag, message)
        }
    }
}