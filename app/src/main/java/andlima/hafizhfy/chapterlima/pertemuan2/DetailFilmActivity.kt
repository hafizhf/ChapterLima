package andlima.hafizhfy.chapterlima.pertemuan2

import andlima.hafizhfy.chapterlima.R
import andlima.hafizhfy.chapterlima.pertemuan2.model.GetAllFilmResponseItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        val detailFilm = intent.getParcelableExtra<GetAllFilmResponseItem>("DETAIL_FILM")

        tv_judul_detail.text = detailFilm?.name
        Glide.with(this).load(detailFilm?.image).into(iv_thumbnail_detail)
        tv_tanggal_detail.text = detailFilm?.date
        tv_sutradara_detail.text = detailFilm?.director
        tv_deskripsi_detail.text = detailFilm?.description


    }
}