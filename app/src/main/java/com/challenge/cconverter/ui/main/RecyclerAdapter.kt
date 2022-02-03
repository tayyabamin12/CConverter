package com.challenge.cconverter.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.challenge.cconverter.R

class RecyclerAdapter(var context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var dataList: Array<String>

    internal fun setDataList(dataList: Array<String>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_c_name)
        var currency: TextView = itemView.findViewById(R.id.tv_c_value)
    }

  // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
    
      // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_item, parent, false)
        return ViewHolder(view)
    }

 // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
    
     // Get the data model based on position
        var data = dataList[position]

       // Set item views based on your views and data model
        holder.title.text = data
        holder.currency.text = "12.99"
    }

 //  total count of items in the list
    override fun getItemCount() = dataList.size
}