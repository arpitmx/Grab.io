package com.ncs.grabio.UI.Home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.ncs.grabio.R


/**
 * Created by Alok Ranjan on 30-09-2022
 * Project : NCS Grab.io
 * Description :
 * Github : https://github.com/arpitmx
 **/
class RecyclerViewAdapter(context: Context, list: ArrayList<DHolder.Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
        const val VIEW_TYPE_THREE = 3

    }

    private val context: Context = context
    var list: ArrayList<DHolder.Data> = list

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            //message.text = recyclerViewModel.textData
        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
        }
    }

    private inner class View3ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {


        val coursesGV = itemView.findViewById<GridView>(R.id.gridproductlayout)
        val courseModelArrayList: ArrayList<DHolder.DataGrid> = ArrayList()
        courseModelArrayList.add(DHolder.DataGrid("DSA", 1))
        courseModelArrayList.add(DHolder.DataGrid("DSA", 1))
        courseModelArrayList.add(DHolder.DataGrid("DSA", 1))
        courseModelArrayList.add(DHolder.DataGrid("DSA", 1))
        courseModelArrayList.add(DHolder.DataGrid("DSA", 1))
        courseModelArrayList.add(DHolder.DataGrid("DSA", 1))
        val adapter = GridAdapter(context, courseModelArrayList)
        coursesGV.setAdapter(adapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.searchbar_item, parent, false)
            )
        }
        else if (viewType == VIEW_TYPE_THREE){
           return View3ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.opportunities_grid_item, parent, false))
        }
        else {
            return View2ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.poster_item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType === VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        }
        else if (list[position].viewType === VIEW_TYPE_THREE){
            (holder as View3ViewHolder).bind(position)
        }

        else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}