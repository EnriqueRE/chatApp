package chat.at.gse.com.chatapp.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Message

class FileMessageView(view: View) : RecyclerView.ViewHolder(view) {
    val imageProfile: ImageView = view.findViewById(R.id.image_message_profile)
    val messageBody: TextView = view.findViewById(R.id.text_message_body)

    fun bind(message: Message){
        val payload = message.messageBody.messagePayload
        messageBody.text = payload.attachment?.url
    }
}