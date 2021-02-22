package com.example.dbsearcher.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.decode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbsearcher.R
import com.example.dbsearcher.ResultsAdapter
import com.example.dbsearcher.ResultsItem
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityResultsBinding
import kotlin.collections.ArrayList

class ResultsActivity : AppCompatActivity() {

    private val recyclerList = ArrayList<ResultsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityResultsBinding.inflate(layoutInflater).root
        setContentView(view)

        val intent = intent
        val personData =
            intent.getSerializableExtra(MainActivity.PERSONDATA) as ArrayList<PersonJson>

        populateRecyclerList(personData, personData.size)


        val resultsRecycler: RecyclerView = findViewById(R.id.recyclerView)
        resultsRecycler.adapter = ResultsAdapter(recyclerList)
        resultsRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun populateRecyclerList(results: ArrayList<PersonJson>?, resultSize: Int?) {
        for (i in 0 until resultSize!!/2) {
            var imageBytes: ByteArray
            if (results?.get(i)?.image_code?.get(0) == '/') {
                imageBytes = decode(results?.get(i)?.image_code, DEFAULT)
            } else {
                var doubleDecodedIMG = decode(results?.get(i)?.image_code, DEFAULT)
                imageBytes = decode(doubleDecodedIMG, DEFAULT)
            }
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            val recyclerItem = ResultsItem(
                "${results?.get(i)?.first_name}  ${results?.get(i)?.last_name}",
                "${results?.get(i)?.private_number}",
                "2003",
                decodedImage
            )
            recyclerList += recyclerItem
        }
    }

}