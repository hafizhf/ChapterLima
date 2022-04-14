package andlima.hafizhfy.chapterlima.pertemuan2.network

import andlima.hafizhfy.chapterlima.pertemuan2.model.GetAllFilmResponseItem
import andlima.hafizhfy.chapterlima.pertemuan2.model.PostFilmResponse
import andlima.hafizhfy.chapterlima.pertemuan2.model.RequestFilm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("film")
    fun getAllFilm() : Call<List<GetAllFilmResponseItem>>

    @POST("film")
    fun postFilm(@Body req : RequestFilm) : Call<PostFilmResponse>
}