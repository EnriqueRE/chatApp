package chat.at.gse.com.chatapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import chat.at.gse.com.chatapp.ChatMessageFragment.OnListFragmentInteractionListener
import chat.at.gse.com.chatapp.dummy.DummyContent.DummyItem
import chat.at.gse.com.chatapp.model.MessageSent
import chat.at.gse.com.chatapp.view.SentMessageView

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ChatMessageRecyclerViewAdapter(
        private val mValues: List<MessageSent>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = mValues[position]

        (holder as SentMessageView).bind(item);
    }

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
//            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_sent_message, parent, false)
        return SentMessageView(view)
    }


    override fun getItemCount(): Int = mValues.size


}
