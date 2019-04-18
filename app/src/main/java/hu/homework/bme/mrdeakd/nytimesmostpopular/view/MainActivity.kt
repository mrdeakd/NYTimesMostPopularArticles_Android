package hu.homework.bme.mrdeakd.nytimesmostpopular.view

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import hu.homework.bme.mrdeakd.nytimesmostpopular.R
import hu.homework.bme.mrdeakd.nytimesmostpopular.adapter.ArticleItem
import kotlinx.android.synthetic.main.activity_main.*
import hu.homework.bme.mrdeakd.nytimesmostpopular.dbmodel.ArticleToShow
import hu.homework.bme.mrdeakd.nytimesmostpopular.networkAvailable
import hu.homework.bme.mrdeakd.nytimesmostpopular.viewmodel.ArticleViewModel


class MainActivity : AppCompatActivity() {

    private val adapter = GroupAdapter<ViewHolder>()
    private lateinit var aViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_items.adapter = adapter
        rv_items.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        aViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(ArticleViewModel::class.java)

        aViewModel.allArticles.observe(this, android.arch.lifecycle.Observer<List<ArticleToShow>> { l ->
            adapter.clear()
            for (item in l!!) {
                adapter.add(
                    ArticleItem(
                        item.byline,
                        item.published_date,
                        item.title,
                        item.smallpic,
                        item.largepic,
                        item.url,
                        this@MainActivity
                    )
                )

            }
        })

        swipe_layout.setOnRefreshListener {
            aViewModel.getNewArticles()
            swipe_layout.isRefreshing = false
        }
        networkAvailable(this, swipe_layout)
    }
}
