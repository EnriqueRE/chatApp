package chat.at.gse.com.chatapp.ViewAdapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Card
import com.squareup.picasso.Picasso
import com.viewpagerindicator.CirclePageIndicator

class HorizontalCardAdapter(private val context: Context, private val cards:List<Card>): PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        //        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.view_card, collection, false) as ViewGroup

        val card = cards[position]
        val txtTitle: TextView = layout.findViewById(R.id.cardTitle)
        val txtDescription: TextView = layout.findViewById(R.id.cardDescription)
        val btnCard:Button = layout.findViewById(R.id.cardButton)
        val image:ImageView = layout.findViewById(R.id.cardImage)


        txtTitle.text = card.title
        txtDescription.text = card.description
        btnCard.text = card.actions?.get(0)?.label
        Picasso.get().load(card.imageUrl).into(image)

        collection.addView(layout)


        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return cards.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}