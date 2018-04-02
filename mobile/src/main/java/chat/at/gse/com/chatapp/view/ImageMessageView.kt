package chat.at.gse.com.chatapp.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Message
import com.squareup.picasso.Picasso

class ImageMessageView(view: View) : RecyclerView.ViewHolder(view) {
    var txtMessageBody: TextView
    var image: ImageView

    init {
        txtMessageBody = view.findViewById(R.id.text_message_body)
        image = view.findViewById(R.id.image)
    }

    fun bind(message: Message) {
        val payload = message.messageBody.messagePayload

        if (payload.text == "") {
            txtMessageBody.visibility = View.GONE
        } else {
            txtMessageBody.text = payload.text
        }
//        Log.d("IMAGEVIEW", payload.attachment.toString())
        Picasso.get().load(payload.attachment!!.url).into(image)
    }

}