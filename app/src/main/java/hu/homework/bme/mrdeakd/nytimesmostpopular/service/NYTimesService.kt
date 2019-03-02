package hu.homework.bme.mrdeakd.nytimesmostpopular.service

import hu.homework.bme.mrdeakd.nytimesmostpopular.data.Response
import retrofit2.Call
import retrofit2.http.GET

interface NYTimesService {
    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=o7sksrGPWxvuRIi8ISHbEmBKi7srjI6u")
    fun getArticles(): Call<Response>
}