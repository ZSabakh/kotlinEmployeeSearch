package com.example.dbsearcher.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dbsearcher.R
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityDetailBinding
import com.example.dbsearcher.databinding.ActivityMainBinding
import com.example.dbsearcher.utility.imageDecoder
import java.sql.Date
import java.sql.Timestamp

class DetailActivity : AppCompatActivity() {
    private var personData = PersonJson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        personData = intent.getSerializableExtra(ResultsActivity.DETAILDATA) as PersonJson

        val view = ActivityDetailBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityDetailBinding.bind(view).onViewBind()
    }

    private fun ActivityDetailBinding.onViewBind() {
        tvDetailName.text = "${personData.first_name} ${personData.last_name}"

        val stamp = personData.birth_date.toLong().let { Timestamp(it) }
        val date = Date(stamp.time)

        tvDetailDob.text = date.toString()
        tvDetailLivingPlace.text = personData.living_place
        tvDetailPrivateNumber.text = personData.private_number
        tvDetailGender.text = if (personData.gender == 1) "Male" else "Female"
        tvDetailCitizenshipCode.text = personData.citizenship_code
        ivDetailImage.setImageBitmap(imageDecoder(personData.image_code))
    }
}