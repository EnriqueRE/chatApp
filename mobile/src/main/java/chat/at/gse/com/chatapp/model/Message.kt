package chat.at.gse.com.chatapp.model

import chat.at.gse.com.chatapp.model.payload.MessagePayload
import java.util.*

data class Message (val to:MessageTo?, val from: MessageFrom?, val payload: MessagePayload)

data class MessageTo(val type: String, val id: String)
data class MessageFrom(val type: String, val id: String)