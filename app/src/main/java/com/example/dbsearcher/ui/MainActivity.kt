package com.example.dbsearcher.ui

import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.dbsearcher.ApiRequests
import com.example.dbsearcher.R
import com.example.dbsearcher.api.PersonJson
import com.example.dbsearcher.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://167.99.7.161:4000"

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    var personData: ArrayList<PersonJson>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityMainBinding.bind(view).onViewBind()

        getData()
    }

    private fun getData() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getAllResults = api.getEmployeesPages().awaitResponse()
            val findResults = api.findEmployee("ნიკა", "მგალობლიშვილი").awaitResponse()
            personData = findResults.body()!!


            if (getAllResults.isSuccessful) {
                val data = getAllResults.body()!!
                Log.d(TAG, data[0].private_number)
                withContext(Dispatchers.Main) {
                    val tvRetroTest = findViewById<TextView>(R.id.tvRetroTest)
                    tvRetroTest.text = data[0].first_name
                }
            }
        }
    }

    private fun ActivityMainBinding.onViewBind() {
        btnSearch.setOnClickListener {
            val intent = Intent(applicationContext, ResultsActivity::class.java)
            intent.putExtra(NAME, etName.text.toString())
            intent.putExtra(PERSONDATA, personData)
            intent.putExtra(LASTNAME, etLname.text.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    companion object {
        const val NAME = "name"
        const val LASTNAME = "lastname"
        const val PERSONDATA = "persondata"
    }
}