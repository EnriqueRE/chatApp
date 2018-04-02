package chat.at.gse.com.chatapp.ViewAdapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import chat.at.gse.com.chatapp.fragments.ChatMessageFragment.OnListFragmentInteractionListener
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.dummy.DummyContent.DummyItem
import chat.at.gse.com.chatapp.model.BaseMessage
import chat.at.gse.com.chatapp.model.Message
import chat.at.gse.com.chatapp.model.MessageSent
import chat.at.gse.com.chatapp.view.*
import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ChatMessageRecyclerViewAdapter(
        private val mValues: List<BaseMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        const val VIEW_TYPE_MESSAGE_SENT = 0
        const val VIEW_TYPE_MESSAGE_RECEIVED = 1
        const val VIEW_TYPE_BOT_CHOICES = 2
        const val VIEW_TYPE_BOT_CARDS = 3
        const val VIEW_TYPE_BOT_IMAGE = 4
        const val VIEW_TYPE_BOT_AUDIO = 5
        const val VIEW_TYPE_BOT_VIDEO = 6
        const val VIEW_TYPE_BOT_FILE = 7
    }

    override fun getItemViewType(position: Int): Int {

        var message = mValues[position] as BaseMessage

        when (message.appType) {
            "user" -> return VIEW_TYPE_MESSAGE_SENT
            "bot" -> {

                val payload = (message as Message).messageBody.messagePayload

                return if (payload.type.equals("text") && payload.actions != null) {
//                    Log.d("ADAPTER", payload.actions.toString())
                    VIEW_TYPE_BOT_CHOICES
                } else {

                    when (payload.type) {
                        "attachment" ->
                            when (payload.attachment!!.type) {
                                "image" -> {

                                    VIEW_TYPE_BOT_IMAGE
                                }
                                "audio" -> {
                                    VIEW_TYPE_BOT_AUDIO
                                }
                                "video" -> VIEW_TYPE_BOT_VIDEO
                                "file" -> VIEW_TYPE_BOT_FILE
                                else -> {
                                    Log.d("ADAPTER", "NOTHING")
                                }

                            }
                        else -> VIEW_TYPE_MESSAGE_RECEIVED
                    }
                }


            }


        }

        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = mValues[position]

        when (holder?.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> {
                (holder as SentMessageView).bind(item as MessageSent)
            }
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedTextView).bind(item as Message)
            VIEW_TYPE_BOT_CHOICES -> (holder as MultiChoiceMessageView).bind(item as Message)
            VIEW_TYPE_BOT_IMAGE -> (holder as ImageMessageView).bind(item as Message)
            VIEW_TYPE_BOT_AUDIO -> (holder as AudioMessageView).bind(item as Message)
            VIEW_TYPE_BOT_VIDEO -> (holder as VideoMessageView).bind(item as Message)
            VIEW_TYPE_BOT_FILE -> (holder as FileMessageView).bind(item as Message)
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
            VIEW_TYPE_BOT_CHOICES -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_choices_received_message, parent, false)
                return MultiChoiceMessageView(view)
            }
            VIEW_TYPE_BOT_IMAGE -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_image_received_message, parent, false)
                return ImageMessageView(view)
            }
            VIEW_TYPE_BOT_AUDIO ->{
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_audio_received_message, parent, false)
                return AudioMessageView(view)
            }
            VIEW_TYPE_BOT_VIDEO ->{
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_video_received_message, parent,false)
                return VideoMessageView(view)
            }
            VIEW_TYPE_BOT_FILE->{
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_file_received_message, parent,false)
                return FileMessageView(view)
            }
            else -> return null
        }

    }


    override fun getItemCount(): Int = mValues.size


}
