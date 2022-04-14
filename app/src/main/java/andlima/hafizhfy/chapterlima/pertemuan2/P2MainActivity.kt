package andlima.hafizhfy.chapterlima.pertemuan2

import andlima.hafizhfy.chapterlima.R
import andlima.hafizhfy.chapterlima.pertemuan2.adapter.AdapterFilm
import andlima.hafizhfy.chapterlima.pertemuan2.model.GetAllFilmResponseItem
import andlima.hafizhfy.chapterlima.pertemuan2.network.ApiClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_p2_main.*
import retrofit2.Call
import retrofit2.Response

class P2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p2_main)

        getDataFilm()

        fab_add_film.setOnClickListener {
            startActivity(Intent(this, AddFilmActivity::class.java))
        }
    }

    fun getDataFilm() {
        // 1. Network
        // 2. Endpoint (apiclient, terus apisercive)

        ApiClient.instance.getAllFilm()
            .enqueue(object : retrofit2.Callback<List<GetAllFilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllFilmResponseItem>>,
                    response: Response<List<GetAllFilmResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        val dataFilm = response.body()

                        val adapter = AdapterFilm(dataFilm!!) {
                            val pindah = Intent(this@P2MainActivity, DetailFilmActivity::class.java)
                            pindah.putExtra("DETAIL_FILM", it)
                            startActivity(pindah)
                        }
                        val lm = LinearLayoutManager(
                            applicationContext,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        rv_pertemuan2.layoutManager = lm
                        rv_pertemuan2.adapter = adapter

                    } else {
                        Toast.makeText(this@P2MainActivity, response.message(), Toast.LENGTH_LONG).show()
//                        AlertDialog.Builder(this@P2MainActivity)
//                            .setTitle("Error")
//                            .setMessage(response.message())
//                            .setCancelable(true)
//                            .show()
                    }
                }

                override fun onFailure(call: Call<List<GetAllFilmResponseItem>>, t: Throwable) {
                    Toast.makeText(this@P2MainActivity, t.message, Toast.LENGTH_LONG).show()
//                    AlertDialog.Builder(this@P2MainActivity)
//                        .setTitle("Error")
//                        .setMessage(t.message)
//                        .setCancelable(true)
//                        .show()
                }

            })
    }

    override fun onResume() {
        super.onResume()
        getDataFilm()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}