package com.example.silentai

import okhttp3.*
import java.io.IOException

class AiService {

    private val client = OkHttpClient()

    fun invoke(input: String, onResult: (String) -> Unit) {

        // صمت افتراضي: لا استدعاء بلا إدخال
        if (input.isBlank()) {
            onResult("")
            return
        }

        val requestBody = RequestBody.create(
            "text/plain".toMediaType(),
            SystemPrompt.text + "\n\n" + input
        )

        val request = Request.Builder()
            .url("http://10.0.2.2:8000/api/v1/invoke")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                onResult("")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: ""
                onResult(body)
            }
        })
    }
}
