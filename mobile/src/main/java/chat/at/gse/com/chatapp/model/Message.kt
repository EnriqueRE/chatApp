package chat.at.gse.com.chatapp.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


open class BaseMessage(val appType: String)

data class MessageSent(val from: MessageFrom?,
                       val messageTo: MessageTo?,
                       val messagePayload: MessagePayload) : BaseMessage("user")

data class Message(val from: MessageFrom?,
                   val messageTo: MessageTo?,
                   @Json(name = "body")
                   val messageBody: MessageBody) : BaseMessage("bot")

data class MessageBody(val messagePayload: MessagePayload)

data class MessageTo(val type: String, val id: String)
data class MessageFrom(val type: String, val id: String)

data class MessagePayload(
        val type: String,
        val text: String? = "",
        val postback: Postback? = null,
        val globalActions: List<Action>? = null,
        val actions: List<Action>? = null,
        val attachment: Attachment? = null,
        val location: Location? = null,
        val cards: List<Card>? = null

)

data class Postback(val action: String,
                    val state: String,
                    val variables: Map<String, Object>?)

data class Action(val postback: Postback?,
                  val label: String,
                  val type: String)

data class Attachment(val type: String, val url: String)

data class Location(val longitude: Double, val latitude: Double)

data class Card(val imageUrl: String?,
                val description: String?,
                val title: String?,
                val actions: List<Action>?)