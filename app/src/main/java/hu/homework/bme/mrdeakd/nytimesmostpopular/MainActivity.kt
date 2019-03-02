package hu.homework.bme.mrdeakd.nytimesmostpopular

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import hu.homework.bme.mrdeakd.nytimesmostpopular.adapterdata.ArticleItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.homework.bme.mrdeakd.nytimesmostpopular.data.ArticleToShow
import hu.homework.bme.mrdeakd.nytimesmostpopular.data.Article
import hu.homework.bme.mrdeakd.nytimesmostpopular.model.ItemsViewModel
import hu.homework.bme.mrdeakd.nytimesmostpopular.service.NYTimesService


class MainActivity : AppCompatActivity() {

    private val adapter = GroupAdapter<ViewHolder>()
    private var aViewModel: ItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_items.adapter = adapter
        rv_items.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        aViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(ItemsViewModel::class.java)

        aViewModel!!.allArticles.observe(this, android.arch.lifecycle.Observer<List<ArticleToShow>> { l ->
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
            onLoadFromNYT()
            swipe_layout.isRefreshing = false
        }
        NetworkAvailable(this, swipe_layout)
    }

    override fun onDestroy() {
        aViewModel!!.destoryINSTANCE()
        super.onDestroy()
    }

    private fun onLoadFromNYT() {
        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.NYTimesURL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NYTimesService::class.java)

        val repos = service.getArticles()

        repos.enqueue(object : Callback<hu.homework.bme.mrdeakd.nytimesmostpopular.data.Response> {
            override fun onFailure(call: Call<hu.homework.bme.mrdeakd.nytimesmostpopular.data.Response>, t: Throwable) {
                Log.e("Error", t.message)
            }

            override fun onResponse(
                call: Call<hu.homework.bme.mrdeakd.nytimesmostpopular.data.Response>,
                response: Response<hu.homework.bme.mrdeakd.nytimesmostpopular.data.Response>
            ) {
                if (response.isSuccessful) {
                    val post = response.body()
                    if (!ownEquals(aViewModel!!.allArticles, post!!.results)) {
                        aViewModel!!.deleteAllArticles()
                        for (item in post.results) {
                            for (media in item.media) {
                                var smallPic = ""
                                var largePic = ""
                                for (metaMedia in media.media_metadata!!) {
                                    if (metaMedia.format!!.equals(getString(R.string.Standard_Thumbnail)))
                                        smallPic = metaMedia.url!!
                                    if (metaMedia.format.equals(getString(R.string.Large_Thumbnail)))
                                        largePic = metaMedia.url!!
                                }
                                aViewModel!!.insertArticle(
                                    ArticleToShow(
                                        item.url,
                                        item.adx_keywords,
                                        item.column,
                                        item.section,
                                        item.byline,
                                        item.type,
                                        item.title,
                                        item.abstract,
                                        item.published_date,
                                        item.source,
                                        item.id!!.toInt(),
                                        item.asset_id!!.toInt(),
                                        item.views!!.toInt(),
                                        smallPic,
                                        largePic
                                    )
                                )
                            }
                        }
                    }
                }
            }
        })
    }

    private fun ownEquals(allArticles: LiveData<List<ArticleToShow>>, results: Array<Article>): Boolean {
        var listOfId: MutableList<Int> = mutableListOf()
        for (item in allArticles.value!!)
            listOfId.add(item.id!!.toInt())
        for (item in results) {
            if (item.id!!.toInt() !in listOfId)
                return false
        }
        return true
    }
}
