package chat.at.gse.com.chatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import chat.at.gse.com.chatapp.model.Message
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

class MainActivity : AppCompatActivity() {

    val TAG:String = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val location = """
 {
    "to": {
        "type": "bot",
        "id": "6EF3D456-DEB9-46BB-A58D-3FDDF965A0C2"
    },
    "body": {
        "messagePayload": {
            "type": "location",
            "location": {
                "longitude": -106.13751069999999,
                "latitude": 28.689817599999998
            }
        }
    }
}
        """.trimIndent()

        val video = """

            {
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
}

            """

        val cards ="""
            {
    "from": {
        "type": "bot",
        "id": "1FB0EF34-3726-4C75-917C-924371E52797"
    },
    "body": {
        "messagePayload": {
            "layout": "vertical",
            "cards": [{
                "imageUrl": "https://cdn.pixabay.com/photo/2017/09/03/10/35/pizza-2709845__340.jpg",
                "description": "Classic marinara sauce topped with whole milk mozzarella cheese.",
                "title": "CHEESE",
                "actions": [{
                    "postback": {
                        "variables": {
                            "orderedPizzaImage": "https://cdn.pixabay.com/photo/2017/09/03/10/35/pizza-2709845__340.jpg",
                            "orderedPizza": "CHEESE"
                        },
                        "action": "order",
                        "state": "OrderPizza"
                    },
                    "label": "Order Now",
                    "type": "postback"
                }]
            }, {
                "imageUrl": "https://cdn.pixabay.com/photo/2017/08/02/12/38/pepperoni-2571392__340.jpg",
                "description": "Classic marinara sauce with authentic old-world style pepperoni.",
                "title": "PEPPERONI",
                "actions": [{
                    "postback": {
                        "variables": {
                            "orderedPizzaImage": "https://cdn.pixabay.com/photo/2017/08/02/12/38/pepperoni-2571392__340.jpg",
                            "orderedPizza": "PEPPERONI"
                        },
                        "action": "order",
                        "state": "OrderPizza"
                    },
                    "label": "Order Now",
                    "type": "postback"
                }]
            }, {
                "imageUrl": "https://cdn.pixabay.com/photo/2017/07/22/22/51/big-2530144__340.jpg",
                "description": "Classic marinara sauce, authentic old-world pepperoni, all-natural Italian sausage, slow-roasted ham, hardwood smoked bacon, seasoned pork and beef.",
                "title": "MEAT LOVER",
                "actions": [{
                    "postback": {
                        "variables": {
                            "orderedPizzaImage": "https://cdn.pixabay.com/photo/2017/07/22/22/51/big-2530144__340.jpg",
                            "orderedPizza": "MEAT LOVER"
                        },
                        "action": "order",
                        "state": "OrderPizza"
                    },
                    "label": "Order Now",
                    "type": "postback"
                }]
            }, {
                "imageUrl": "https://cdn.pixabay.com/photo/2017/07/22/22/57/pizza-2530169__340.jpg",
                "description": "Classic marinara sauce, authentic old-world pepperoni, seasoned pork, beef, fresh mushrooms, fresh green bell peppers and fresh red onions.",
                "title": "SUPREME",
                "actions": [{
                    "postback": {
                        "variables": {
                            "orderedPizzaImage": "https://cdn.pixabay.com/photo/2017/07/22/22/57/pizza-2530169__340.jpg",
                            "orderedPizza": "SUPREME"
                        },
                        "action": "order",
                        "state": "OrderPizza"
                    },
                    "label": "Order Now",
                    "type": "postback"
                }]
            }],
            "type": "card",
            "actions": [{
                "postback": {
                    "variables": {
                        "cardsRangeStart": "4"
                    },
                    "action": "more",
                    "state": "OrderPizza"
                },
                "label": "More Pizzas",
                "type": "postback"
            }],
            "channelExtensions": {}
        },
        "userId": "1"
    }
}
        """.trimIndent()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val messageAdapter = moshi.adapter(Message::class.java)
//        val message = messageAdapter.fromJson(textSample)
//        val postbackMessage = messageAdapter.fromJson(postbackJson)
//        val choicesMessage = messageAdapter.fromJson(choices)
//
//        output("Text Sample type: ${message?.messagePayload?.type}")
//        output("Type of postback: ${postbackMessage?.messagePayload?.type} + action: ${postbackMessage?.messagePayload?.postback.toString()}")
//        output("type: ${choicesMessage?.messageBody?.messagePayload?.type} + actions ${choicesMessage?.messageBody?.messagePayload.toString()}")

        val message = messageAdapter.fromJson(location);
        val vidMessage = messageAdapter.fromJson(video)
        val cardsMessage = messageAdapter.fromJson(cards)

//        output(message.toString())
//        output(vidMessage.toString())
        output(cardsMessage.toString())







    }

    fun output(text: String?){
        Log.d(TAG, text);
    }


}
