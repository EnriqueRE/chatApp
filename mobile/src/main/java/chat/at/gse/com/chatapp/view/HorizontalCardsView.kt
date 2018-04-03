package chat.at.gse.com.chatapp.view

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.ViewAdapters.HorizontalCardAdapter
import chat.at.gse.com.chatapp.model.Message

class HorizontalCardsView(view:View) : RecyclerView.ViewHolder(view) {

    private val pager: ViewPager

    init {
        pager = view.findViewById(R.id.pager)
    }

    fun bind(messge:Message){
        Log.d("HORIZONTAL CARD VIEW", "binding")
        val payload = messge.messageBody.messagePayload
//        Log.d("HORIZONTAL CARD VIEW", payload.cards.toString())
        val adapter = HorizontalCardAdapter(pager.context, payload.cards!!)
        pager.adapter = adapter
    }

}