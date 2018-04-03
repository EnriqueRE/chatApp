package chat.at.gse.com.chatapp.view

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.ViewAdapters.HorizontalCardAdapter
import chat.at.gse.com.chatapp.model.Message
import com.viewpagerindicator.CirclePageIndicator

class HorizontalCardsView(view:View) : RecyclerView.ViewHolder(view) {

    private val pager: ViewPager
    private val view: View
    init {
        pager = view.findViewById(R.id.pager)
        this.view = view
    }



    fun bind(messge:Message){
        Log.d("HORIZONTAL CARD VIEW", "binding")
        val payload = messge.messageBody.messagePayload
//        Log.d("HORIZONTAL CARD VIEW", payload.cards.toString())
        val adapter = HorizontalCardAdapter(pager.context, payload.cards!!)
        val indicator = view.findViewById<CirclePageIndicator>(R.id.indicator)
        pager.adapter = adapter
        indicator.setViewPager(pager)
    }

}