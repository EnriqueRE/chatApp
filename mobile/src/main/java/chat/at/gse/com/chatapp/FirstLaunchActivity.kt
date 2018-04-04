package chat.at.gse.com.chatapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_first_launch.*

class FirstLaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_launch)
    }

    fun startFlow(view:View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}
