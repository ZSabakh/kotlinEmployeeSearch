package com.example.dbsearcher.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbsearcher.R
import com.example.dbsearcher.ResultsAdapter
import com.example.dbsearcher.ResultsItem
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {

    private val recyclerList = ArrayList<ResultsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityResultsBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityResultsBinding.bind(view).onViewBind()

        val intent = intent
        val personData = intent.getSerializableExtra(MainActivity.PERSONDATA) as ArrayList<PersonJson>?

        populateRecyclerList(personData, personData?.size)


        var resultsRecycler: RecyclerView = findViewById(R.id.recyclerView)
        resultsRecycler.adapter = ResultsAdapter(recyclerList)
        resultsRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun populateRecyclerList(results: ArrayList<PersonJson>?, resultSize: Int?) {
        for (i in 0 until resultSize!!){
            val recyclerItem = ResultsItem("${results?.get(i)?.first_name}  ${results?.get(i)?.last_name}","${results?.get(i)?.private_number}","2003")
            recyclerList += recyclerItem
        }
    }


    private fun ActivityResultsBinding.onViewBind(){


    }
}