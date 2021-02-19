package com.example.dbsearcher.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbsearcher.R
import com.example.dbsearcher.ResultsAdapter
import com.example.dbsearcher.ResultsItem
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {

    val recyclerTest = ArrayList<ResultsItem>()
    val recyclerItemOne = ResultsItem("Giorgi")
    val recyclerItemTwo = ResultsItem("Misha")
    val recyclerItemThree = ResultsItem("Vano")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityResultsBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityResultsBinding.bind(view).onViewBind()

        var resultsRecycler: RecyclerView = findViewById(R.id.recyclerView)
        recyclerTest += recyclerItemOne
        recyclerTest += recyclerItemTwo
        recyclerTest += recyclerItemThree

        resultsRecycler.adapter = ResultsAdapter(recyclerTest)
        resultsRecycler.layoutManager = LinearLayoutManager(this)
    }
    var searchName = "default"

    private fun ActivityResultsBinding.onViewBind(){
        val tvSearchName = findViewById<TextView>(R.id.tvSearchName)
        tvSearchName.text = searchName

        val intent = intent
        val name = intent.getStringExtra(MainActivity.NAME).toString()
        val lastName = intent.getStringExtra(MainActivity.LASTNAME).toString()
        val personData = intent.getSerializableExtra(MainActivity.PERSONDATA) as ArrayList<PersonJson>?

        println(personData)
        println(personData?.get(0)?.first_name)
        println(personData?.get(1)?.first_name)

        tvSearchName.text = "$name $lastName"
        searchName = name
    }
}