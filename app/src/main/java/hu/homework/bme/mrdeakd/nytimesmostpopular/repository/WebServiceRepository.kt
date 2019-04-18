package hu.homework.bme.mrdeakd.nytimesmostpopular.repository

import android.app.Application
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.arch.lifecycle.LiveData
import android.util.Log
import hu.homework.bme.mrdeakd.nytimesmostpopular.R
import hu.homework.bme.mrdeakd.nytimesmostpopular.apimodel.Response
import hu.homework.bme.mrdeakd.nytimesmostpopular.dbmodel.ArticleToShow
import hu.homework.bme.mrdeakd.nytimesmostpopular.viewmodel.ViewModelCallback
import hu.homework.bme.mrdeakd.nytimesmostpopular.webservice.NYTimesService
import retrofit2.Call
import retrofit2.Callback


class WebServiceRepository(private val application: Application, private val viewmodelcallback : ViewModelCallback) {

    var webserviceResponseList: MutableList<ArticleToShow> = mutableListOf()

    fun providesWebService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(application.getString(R.string.NYTimesURL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NYTimesService::class.java)

        val repos = service.getArticles()

        repos.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Error", t.message)
            }

            override fun onResponse(
                call: Call<Response>, response: retrofit2.Response<Response>
            ) {
                if (response.isSuccessful) {
                    val post = response.body()!!
                    webserviceResponseList.clear()
                    for (item in post.results) {
                        for (media in item.media) {
                            var smallPic = ""
                            var largePic = ""
                            for (metaMedia in media.media_metadata) {
                                if (metaMedia.format == application.getString(R.string.Standard_Thumbnail))
                                    smallPic = metaMedia.url
                                if (metaMedia.format == application.getString(R.string.Large_Thumbnail))
                                    largePic = metaMedia.url
                            }
                            webserviceResponseList.add(
                                ArticleToShow(
                                    item.url,
                                    item.byline,
                                    item.title,
                                    item.published_date,
                                    item.id.toInt(),
                                    smallPic,
                                    largePic
                                )
                            )
                        }
                    }
                    val currentItems = viewmodelcallback.getAllArticles()
                    if(!ownEquals(currentItems,webserviceResponseList)){
                        viewmodelcallback.deleteAllArticles()
                        for(item in webserviceResponseList)
                            viewmodelcallback.insertArticle(item)
                    }
                }
            }
        })
    }

    private fun ownEquals(allArticles: LiveData<List<ArticleToShow>>, results: MutableList<ArticleToShow>): Boolean {
        val listOfId: MutableList<Int> = mutableListOf()
            for (item in allArticles.value!!)
                listOfId.add(item.id)
            for (item in results) {
                if (item.id !in listOfId)
                    return false
            }
        return true
    }
}