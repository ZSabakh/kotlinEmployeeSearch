package com.example.dbsearcher.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbsearcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater).root
        setContentView(view)
        ActivityMainBinding.bind(view).onViewBind()
    }

    private fun ActivityMainBinding.onViewBind() {
        btnSearch.setOnClickListener {
            val intent = Intent(applicationContext, ResultsActivity::class.java)
            intent.putExtra(NAME, etName.text.toString())
            intent.putExtra(LASTNAME, etLname.text.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    companion object {
        const val NAME = "name"
        const val LASTNAME = "lastname"

    }
}