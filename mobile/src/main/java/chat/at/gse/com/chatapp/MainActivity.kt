package chat.at.gse.com.chatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import chat.at.gse.com.chatapp.dummy.DummyContent
import chat.at.gse.com.chatapp.fragments.ChatMessageFragment

class MainActivity : AppCompatActivity(), ChatMessageFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val TAG: String = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//        val messageAdapter = moshi.adapter(Message::class.java)
//        val message = messageAdapter.fromJson(textSample)




    }

    fun output(text: String?) {
        Log.d(TAG, text);
    }

}
