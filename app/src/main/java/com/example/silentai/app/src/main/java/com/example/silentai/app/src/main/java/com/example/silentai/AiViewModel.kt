package com.example.silentai

import androidx.lifecycle.ViewModel

class AiViewModel : ViewModel() {

    private val aiService = AiService()

    /**
     * استدعاء صريح فقط
     * لا رد إذا كان الإدخال فارغًا
     */
    fun invoke(input: String, onResult: (String) -> Unit) {
        if (input.isBlank()) {
            onResult("")
            return
        }

        aiService.invoke(input, onResult)
    }
}
