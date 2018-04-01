package chat.at.gse.com.chatapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.ViewAdapters.ChatMessageRecyclerViewAdapter

import chat.at.gse.com.chatapp.dummy.DummyContent.DummyItem
import chat.at.gse.com.chatapp.model.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ChatMessageFragment.OnListFragmentInteractionListener] interface.
 */
class ChatMessageFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var adapter:ChatMessageRecyclerViewAdapter? = null

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chatmessage_list, container, false)

        val messages = ArrayList<BaseMessage>()

        val sent:MessageSent = MessageSent(MessageFrom("user","me"),
                null, MessagePayload("text", "hello world"))

        val receivedText = Message(MessageFrom("bot","123"),
                null,
                MessageBody(MessagePayload("text", "Hello There"))
        )
        messages.add(receivedText)
        messages.add(sent)

        adapter = ChatMessageRecyclerViewAdapter(messages)

        if (view is RecyclerView){
            val context = view.context
            view.adapter = adapter
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                ChatMessageFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
