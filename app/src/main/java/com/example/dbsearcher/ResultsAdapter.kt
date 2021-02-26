package com.example.dbsearcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ResultsAdapter(
    private val resultList: List<ResultsItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {


    inner class ResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tvResultName: TextView
        val tvResultPrivateNumber: TextView
        val tvResultDOB: TextView
        val tvResultAddress: TextView
        val ivPerson: ImageView

        init {
            itemView.setOnClickListener(this)

            tvResultName = itemView.findViewById(R.id.tvResultName)
            tvResultPrivateNumber = itemView.findViewById(R.id.tvResultPrivateNumber)
            tvResultDOB = itemView.findViewById(R.id.tvResultDOB)
            tvResultAddress = itemView.findViewById(R.id.tvResultAddress)
            ivPerson = itemView.findViewById(R.id.ivPerson)
        }

        override fun onClick(v: View?) {
            listener.onItemClick()
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

        holder.tvResultName.text = currentItem.name
        holder.tvResultPrivateNumber.text = currentItem.privateNumber
        holder.tvResultDOB.text = currentItem.dateOfBirth
        holder.tvResultAddress.text = currentItem.address
        holder.ivPerson.setImageBitmap(currentItem.imageCode)
    }

    override fun getItemCount() = resultList.size

    interface OnItemClickListener {
        fun onItemClick()
    }
}