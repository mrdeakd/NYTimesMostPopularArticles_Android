package hu.homework.bme.mrdeakd.nytimesmostpopular.adapterdata

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.squareup.picasso.Picasso
import hu.homework.bme.mrdeakd.nytimesmostpopular.DetailActivity
import hu.homework.bme.mrdeakd.nytimesmostpopular.R
import kotlinx.android.synthetic.main.row_item.view.*

class ArticleItem(private val byline : String?, private val published_date : String?, private val title : String?, private val smallpic : String?, private val largepic : String?, private val url : String?,private val context : Context): com.xwray.groupie.Item<com.xwray.groupie.ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.row_item
    }

    override fun bind(viewHolder: com.xwray.groupie.ViewHolder, position: Int) {
        viewHolder.itemView.tv_by.text = byline
        viewHolder.itemView.tv_date.text = published_date
        viewHolder.itemView.tv_title.text = title
        Picasso.get().load(smallpic).into(viewHolder.itemView.iv_imageofarticle)
        viewHolder.itemView.iv_opendetailactivity.setOnClickListener{
            val myintent = Intent()
            myintent.setClass(context, DetailActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(context,
                R.anim.abc_fade_in,
                R.anim.abc_fade_out
            )
            myintent.putExtra(context.getString(R.string.byline),byline)
            myintent.putExtra(context.getString(R.string.published_date),published_date)
            myintent.putExtra(context.getString(R.string.title),title)
            myintent.putExtra(context.getString(R.string.largepic),largepic)
            myintent.putExtra(context.getString(R.string.url),url)
            startActivity(context,myintent,options.toBundle())
        }
    }
}