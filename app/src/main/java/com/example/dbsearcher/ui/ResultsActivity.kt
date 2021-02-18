package com.example.dbsearcher.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dbsearcher.R
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ActivityResultsBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityResultsBinding.bind(view).onViewBind()


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