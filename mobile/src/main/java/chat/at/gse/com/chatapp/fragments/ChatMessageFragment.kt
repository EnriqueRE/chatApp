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
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ChatMessageFragment.OnListFragmentInteractionListener] interface.
 */
class ChatMessageFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var adapter: ChatMessageRecyclerViewAdapter? = null

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

        val sent: MessageSent = MessageSent(MessageFrom("user", "me"),
                null, MessagePayload("text", "hello world"))

        val receivedText = Message(MessageFrom("bot", "123"),
                null,
                MessageBody(MessagePayload("text", "Hello There"))
        )

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val messageAdapter = moshi.adapter(Message::class.java)

        val choicesMessage = messageAdapter.fromJson(choicesJson)
        val imageMessage = messageAdapter.fromJson(imageJson)
        val audioMessage = messageAdapter.fromJson(audioJson)
        val videoMessage = messageAdapter.fromJson(videoJson)
        val fileMessage = messageAdapter.fromJson(fileJson)
        val horizontalCardsMessage = messageAdapter.fromJson(horizontalCardsJson)

        messages.add(receivedText)
        if (choicesMessage != null) {
            messages.add(choicesMessage)
        }

        if (imageMessage != null) {
            messages.add(imageMessage)
        }

        if (audioMessage != null) {
            messages.add(audioMessage)
        }

        if (videoMessage !=null)
            messages.add(videoMessage)

        if (fileMessage !=null)
            messages.add(fileMessage)
        messages.add(sent)

        if(horizontalCardsMessage !=null){
            messages.add(horizontalCardsMessage)
        }


        adapter = ChatMessageRecyclerViewAdapter(messages)

        if (view is RecyclerView) {
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


        val choicesJson = """{
    "from": {
        "type": "bot",
        "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
    },
    "body": {
        "messagePayload": {
            "text": "Here is a list of the UI features.\nTap on each feature to see a demo.",
            "type": "text",
            "globalActions": [{
                "postback": {
                    "variables": {},
                    "action": "location",
                    "state": "FeatureMenu"
                },
                "label": "Location",
                "type": "postback"
            }],
            "actions": [{
                "postback": {
                    "variables": {},
                    "action": "horiCards",
                    "state": "FeatureMenu"
                },
                "label": "Horizontal Cards",
                "type": "postback"
            }, {
                "postback": {
                    "variables": {},
                    "action": "vertCards",
                    "state": "FeatureMenu"
                },
                "label": "Vertical Cards",
                "type": "postback"
            }, {
                "postback": {
                    "variables": {},
                    "action": "attachment",
                    "state": "FeatureMenu"
                },
                "label": "Attachments",
                "type": "postback"
            }]
        },
        "userId": "1"
    }
} """
        val imageJson = """{
            "from": {
                "type": "bot",
                "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
            },
            "body": {
                "messagePayload": {
                    "attachment": {
                        "type": "image",
                        "url": "https://cloud.oracle.com/res/images/header/oracle-cloud-logo.png"
                    },
                    "type": "attachment"
                },
                "userId": "1"
            }
        }"""
        val audioJson = """{
    "from": {
        "type": "bot",
        "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
    },
    "body": {
        "messagePayload": {
            "attachment": {
                "type": "audio",
                "url": "https://raw.githubusercontent.com/fbsamples/messenger-platform-samples/master/node/public/assets/sample.mp3"
            },
            "type": "attachment"
        },
        "userId": "1"
    }
}"""
        val videoJson = """{
    "from": {
        "type": "bot",
        "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
    },
    "body": {
        "messagePayload": {
            "attachment": {
                "type": "video",
                "url": "https://raw.githubusercontent.com/fbsamples/messenger-platform-samples/master/node/public/assets/allofus480.mov"
            },
            "type": "attachment"
        },
        "userId": "1"
    }
}"""
        val fileJson = """{
    "from": {
        "type": "bot",
        "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
    },
    "body": {
        "messagePayload": {
            "attachment": {
                "type": "file",
                "url": "http://www.oracle.com/technetwork/developer-tools/adf/adf-11-overview-1-129504.pdf"
            },
            "type": "attachment"
        },
        "userId": "1"
    }
}"""
        val horizontalCardsJson = """
            {
    "from": {
        "type": "bot",
        "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
    },
    "body": {
        "messagePayload": {
            "layout": "horizontal",
            "cards": [{
                "imageUrl": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/2/000/123/20e/30acc07.jpg",
                "description": "Demo Builder God",
                "title": "Martin Deh",
                "actions": [{
                    "phoneNumber": "16505060096",
                    "label": "Call Martin",
                    "type": "call"
                }, {
                    "label": "Share",
                    "type": "share"
                }],
                "url": "https://www.linkedin.com/in/martin-deh-4862572"
            }, {
                "imageUrl": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/1/005/074/285/29f51d6.jpg",
                "description": "Multi-lingual Magician",
                "title": "Tamer Qumhieh",
                "actions": [{
                    "postback": {
                        "variables": {
                            "f": "1"
                        },
                        "action": "bio",
                        "state": "HoriCards"
                    },
                    "label": "Bio",
                    "type": "postback"
                }, {
                    "phoneNumber": "962799702703",
                    "label": "Call Tamer",
                    "type": "call"
                }, {
                    "label": "Share",
                    "type": "share"
                }],
                "url": "https://www.linkedin.com/in/tamerqumhieh"
            }, {
                "imageUrl": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAASGAAAAJDEzNDIxNDEzLWJkNmEtNDJmNi04YmM1LWUzNTM3MWM4MzNjOA.jpg",
                "description": "Flow Builder Fanatic",
                "title": "Steven Davelaar",
                "actions": [{
                    "postback": {
                        "variables": {
                            "f": "2"
                        },
                        "action": "bio",
                        "state": "HoriCards"
                    },
                    "label": "Bio",
                    "type": "postback"
                }, {
                    "phoneNumber": "31306699000",
                    "label": "Call Steven",
                    "type": "call"
                }, {
                    "label": "Share",
                    "type": "share"
                }],
                "url": "https://www.linkedin.com/in/steven-davelaar-97a418"
            }, {
                "imageUrl": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/3/000/053/3ae/2aaf5dd.jpg",
                "description": "Machine Learning Master",
                "title": "Lyudmil Pelov",
                "actions": [{
                    "postback": {
                        "variables": {
                            "f": "3"
                        },
                        "action": "bio",
                        "state": "HoriCards"
                    },
                    "label": "Bio",
                    "type": "postback"
                }, {
                    "phoneNumber": "494089091591",
                    "label": "Call Lyudmil",
                    "type": "call"
                }, {
                    "label": "Share",
                    "type": "share"
                }],
                "url": "https://www.linkedin.com/in/lyudmil-pelov-%C2%A9-26209b9"
            }, {
                "imageUrl": "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/3/000/047/38b/26fd5e0.jpg",
                "description": "Docker Devil",
                "title": "Adao Oliviera Junior",
                "actions": [{
                    "phoneNumber": "16507878164",
                    "label": "Call Adao",
                    "type": "call"
                }, {
                    "label": "Share",
                    "type": "share"
                }],
                "url": "https://www.linkedin.com/in/adaojunior/"
            }],
            "type": "card",
            "globalActions": [{
                "postback": {
                    "variables": {},
                    "action": "menu",
                    "state": "HoriCards"
                },
                "label": "Feature Menu",
                "type": "postback"
            }, {
                "label": "A-Team Chronicles",
                "type": "url",
                "url": "http://www.ateam-oracle.com/"
            }]
        },
        "userId": "1"
    }
}
        """.trimIndent()
    }
}
