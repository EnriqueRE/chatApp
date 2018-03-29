package chat.at.gse.com.chatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import chat.at.gse.com.chatapp.model.payload.MessagePayload
import chat.at.gse.com.chatapp.model.Message
import chat.at.gse.com.chatapp.model.MessageFrom
import chat.at.gse.com.chatapp.model.MessageTo
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messagePayload =  MessagePayload("Text");
        val message = Message(MessageTo("bot","1234"), null, messagePayload);

        Log.d("MAIN ACTIVITY", message.toString());

    }
}
