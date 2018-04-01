package chat.at.gse.com.chatapp.ViewAdapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import chat.at.gse.com.chatapp.fragments.ChatMessageFragment.OnListFragmentInteractionListener
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.dummy.DummyContent.DummyItem
import chat.at.gse.com.chatapp.model.BaseMessage
import chat.at.gse.com.chatapp.model.Message
import chat.at.gse.com.chatapp.model.MessageSent
import chat.at.gse.com.chatapp.view.ReceivedTextView
import chat.at.gse.com.chatapp.view.SentMessageView

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ChatMessageRecyclerViewAdapter(
        private val mValues: List<BaseMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        val VIEW_TYPE_MESSAGE_SENT = 0
        val VIEW_TYPE_MESSAGE_RECEIVED = 1
        val VIEW_TYPE_BOT_CHOICES = 2
        val VIEW_TYPE_BOT_CARDS = 3
    }

    override fun getItemViewType(position: Int): Int {

        val message = mValues[position] as BaseMessage

        when (message.appType) {
            "user" -> return VIEW_TYPE_MESSAGE_SENT
            "bot" -> return VIEW_TYPE_MESSAGE_RECEIVED

        }

        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = mValues[position]

        when (holder?.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageView).bind(item as MessageSent)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedTextView).bind(item as Message)
        }


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
        val view: View

        when (viewType) {
            VIEW_TYPE_MESSAGE_SENT -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_sent_message, parent, false)
                return SentMessageView(view);
            }
            VIEW_TYPE_MESSAGE_RECEIVED -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_text_received_message, parent, false)
                return ReceivedTextView(view)
            }
            else -> return null
        }

    }


    override fun getItemCount(): Int = mValues.size


}
