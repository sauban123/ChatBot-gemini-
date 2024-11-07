package com.example.chatbot

import android.view.textclassifier.ConversationActions.Message

data class ChatData (
    val message: String,
    val role : String
)
enum class ChatRoleEnum(val role : String){
    USER("user"),
    MODEL("model")
}

