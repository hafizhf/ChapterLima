package andlima.hafizhfy.chapterlima.pertemuan2

import andlima.hafizhfy.chapterlima.R
import andlima.hafizhfy.chapterlima.pertemuan2.model.PostFilmResponse
import andlima.hafizhfy.chapterlima.pertemuan2.model.RequestFilm
import andlima.hafizhfy.chapterlima.pertemuan2.network.ApiClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_film.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)

        btn_tambah_film.setOnClickListener {
            postDataFilm(
                et_deskripsi_baru.text.toString(),
                et_sutradara_baru.text.toString(),
                et_thumbnail_baru.text.toString(),
                et_judul_baru.text.toString()
            )

            finish()
        }
    }

    fun postDataFilm(
        desc : String,
        director : String,
        img : String,
        name : String
    ) {
        ApiClient.instance.postFilm(RequestFilm(desc,director,img,name))
            .enqueue(object : Callback<PostFilmResponse>{
                override fun onResponse(
                    call: Call<PostFilmResponse>,
                    response: Response<PostFilmResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@AddFilmActivity,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@AddFilmActivity,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PostFilmResponse>, t: Throwable) {
                    Toast.makeText(this@AddFilmActivity, t.message, Toast.LENGTH_LONG).show()
                }

            })
    }
}