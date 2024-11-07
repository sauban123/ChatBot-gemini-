package com.example.chatbot

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatBotVM: ViewModel() {
    val list by lazy {
        mutableStateListOf<ChatData>()
    }
    private val genAI by lazy{
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyAaXrvfdgUdxo_D0c_FQX2fZMQJ5bN8LRI"
        )
    }
    fun sendMessage(message : String) = viewModelScope.launch {
        val chat = genAI.startChat()
        list.add(ChatData(message, ChatRoleEnum.USER.role))
        chat.sendMessage(
            content(ChatRoleEnum.USER.role) { text(message) }
        ).text.let {
            it?.let { it1 -> ChatData(it1,ChatRoleEnum.MODEL.role) }?.let { it2 -> list.add(it2) }
        }

    }
}