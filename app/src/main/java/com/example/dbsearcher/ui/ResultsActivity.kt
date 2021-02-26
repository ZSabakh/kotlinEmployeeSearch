package com.example.dbsearcher.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.decode
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbsearcher.R
import com.example.dbsearcher.ResultsAdapter
import com.example.dbsearcher.ResultsItem
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityResultsBinding
import com.example.dbsearcher.utility.imageDecoder
import java.sql.Date
import java.sql.Timestamp
import kotlin.collections.ArrayList

class ResultsActivity : AppCompatActivity(), ResultsAdapter.OnItemClickListener {

    private val recyclerList = ArrayList<ResultsItem>()
    private var personData = ArrayList<PersonJson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityResultsBinding.inflate(layoutInflater).root
        setContentView(view)

        val intent = intent
        personData = intent.getSerializableExtra(MainActivity.PERSONDATA) as ArrayList<PersonJson>

        populateRecyclerList(personData, personData.size)

        val resultsRecycler: RecyclerView = findViewById(R.id.recyclerView)
        resultsRecycler.adapter = ResultsAdapter(recyclerList, this)
        resultsRecycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClick(pos: Int) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(DETAILDATA, personData[pos])
        startActivity(intent)
    }

    private fun populateRecyclerList(results: ArrayList<PersonJson>?, resultSize: Int?) {
        for (i in 0 until resultSize!! / 2) {
            val decodedImage = imageDecoder(results?.get(i)?.image_code)
            val stamp = results?.get(i)?.birth_date?.toLong()?.let { Timestamp(it) }
            val date = stamp?.getTime()?.let { Date(it) }

            val recyclerItem = ResultsItem(
                "${results?.get(i)?.first_name}  ${results?.get(i)?.last_name}",
                "${results?.get(i)?.private_number}",
                date.toString(),
                decodedImage,
                "${results?.get(i)?.living_place}"
            )
            recyclerList += recyclerItem
        }
    }

    companion object {
        const val DETAILDATA = "detaildata"
    }

}