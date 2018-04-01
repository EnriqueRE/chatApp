package chat.at.gse.com.chatapp.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Message
import kotlinx.android.synthetic.main.view_text_received_message.view.*

class ReceivedTextView (view:View) : RecyclerView.ViewHolder(view) {
    val imageProfile:ImageView = view.findViewById(R.id.image_message_profile)
    val messageBody:TextView = view.findViewById(R.id.text_message_body)

    fun bind(message:Message){
        messageBody.text = message.messageBody.messagePayload.text
    }
}