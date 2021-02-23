package com.example.dbsearcher.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.dbsearcher.ApiRequests
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://167.99.7.161:4000"

class MainActivity : AppCompatActivity() {

    private var personData = ArrayList<PersonJson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityMainBinding.bind(view).onViewBind()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }


    private fun ActivityMainBinding.onViewBind() {
        btnSearch.setOnClickListener {
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequests::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val findResults = api.findEmployee(etName.text, etLname.text).awaitResponse()
                personData = findResults.body()!!
                personData.addAll(findResults.body()!!)
                val intent = Intent(applicationContext, ResultsActivity::class.java)
                intent.putExtra(PERSONDATA, personData)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val PERSONDATA = "persondata"
    }
}