package chat.at.gse.com.chatapp.view

import android.support.v7.widget.RecyclerView
import android.view.TextureView
import android.view.View
import android.widget.TextView
import chat.at.gse.com.chatapp.R

import chat.at.gse.com.chatapp.model.MessageSent



class SentMessageView(view: View): RecyclerView.ViewHolder(view) {

    private val txtMessageBody: TextView = view.findViewById<TextView>(R.id.text_message_body)

    fun bind(message:MessageSent){
        txtMessageBody.text = "hello world"
    }
}