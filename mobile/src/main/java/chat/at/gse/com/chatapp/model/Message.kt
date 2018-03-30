package chat.at.gse.com.chatapp.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class MessageSent(val from: MessageFrom?, val messageTo: MessageTo?, val messagePayload: MessagePayload)
data class Message(val from: MessageFrom?, val messageTo: MessageTo?, @Json(name = "body") val messageBody: MessageBody)

data class MessageBody(val messagePayload: MessagePayload)

data class MessageTo(val type: String, val id: String)
data class MessageFrom(val type: String, val id: String)

data class MessagePayload(
        val type: String,
        val text: String? = "",
        val postback: Postback?,
        val globalActions: List<Action>?,
        val actions: List<Action>?,
        val attachment: Attachment?,
        val location: Location?,
        val cards: List<Card>?

)

data class Postback(val action: String, val state: String, val variables: Map<String, Object>?)
data class Action(val postback: Postback?, val label: String, val type: String)
data class Attachment(val type: String, val url: String)
data class Location(val longitude: Double, val latitude: Double)
data class Card(val imageUrl: String?, val description: String?, val title: String?, val actions: List<Action>?)