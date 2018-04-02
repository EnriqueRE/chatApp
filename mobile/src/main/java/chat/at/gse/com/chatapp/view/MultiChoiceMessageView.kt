package chat.at.gse.com.chatapp.view


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Message
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MultiChoiceMessageView  (view: View): RecyclerView.ViewHolder(view) {

    val mImage: ImageView
    val textMessageName: TextView
    val textMessageBody: TextView
    val textMessageTime: TextView
    val listChoices: ListView

    init {
        mImage = view.findViewById(R.id.image_message_profile)
        textMessageName = view.findViewById(R.id.text_message_name)
        textMessageBody = view.findViewById(R.id.text_message_body)
        textMessageTime = view.findViewById(R.id.text_message_time)
        listChoices = view.findViewById(R.id.listChoices)
    }

    fun bind(message: Message){
        val df = SimpleDateFormat("HH:mm")

        val payload = message.messageBody.messagePayload

        textMessageName.text = "Bot"
        textMessageTime.text = df.format(Date())
        textMessageBody.text = payload.text;

        val choices = ArrayList <String> ()
        for(action in payload.actions!!){
            choices.add(action.label);
        }

        val mAdapter = ArrayAdapter(textMessageName.context,
                android.R.layout.simple_expandable_list_item_1, choices)
        listChoices.adapter = mAdapter


    }

}