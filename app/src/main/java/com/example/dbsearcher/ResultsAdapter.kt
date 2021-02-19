package com.example.dbsearcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultsAdapter(private val resultList: List<ResultsItem>) : RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {


    class ResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.tvResultRow)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.result_row,
            parent, false
        )

        return ResultsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
    val currentItem = resultList[position]

        holder.textView.text = currentItem.name
    }

    override fun getItemCount() = resultList.size
}