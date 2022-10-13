package com.ncs.grabio.UI.Home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.ncs.grabio.R
import com.ncs.grabio.Utility.Later


/**
 * Created by Alok Ranjan on 30-09-2022
 * Project : NCS Grab.io
 * Description : Main recyclerview adapter for home screen
 * Github : https://github.com/arpitmx
 **/

@Later("1. Add bindings to reference the views ")
class RecyclerViewAdapter(context: Context, list: ArrayList<DHolder.Data>, private val callback : Callback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    public interface Callback{
        fun searchBarClicked(item : View)
    }

    companion object {
        const val VIEW_TYPE_SEARCH_BAR = 1
        const val VIEW_TYPE_POSTER = 2
        const val VIEW_TYPE_OPPORTUNITY = 3
        const val VIEW_TYPE_OBSERVE_LIST = 4



    }

    private val context: Context = context
    var list: ArrayList<DHolder.Data> = list

    private inner class Searchbar_ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
           val searchBar = itemView.findViewById<AppCompatButton>(R.id.search_bar_item)

            searchBar.setOnClickListener{
               callback.searchBarClicked(it)
            }

        }
    }

    private inner class Poster_ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
        }
    }

    private inner class ObserveList_ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            val array : ArrayList<Dataholder> = arrayListOf()
            val observeList = itemView.findViewById<ListView>(R.id.observe_list)

            array.add(Dataholder("Codechef","Starters 24",""))
            array.add(Dataholder("Codeforces","Hack 12",""))
            array.add(Dataholder("HackerEarth","23 DaysCode",""))


            val adapter = ObserveListAdapter(context,array)
            observeList.setAdapter(adapter)
        }
    }

    private inner class Opportunity_ViewHolder(itemView: View) :
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
        if (viewType == VIEW_TYPE_SEARCH_BAR) {
            return Searchbar_ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.searchbar_item, parent, false)
            )
        }
        else if (viewType == VIEW_TYPE_OPPORTUNITY){
           return Opportunity_ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.opportunities_grid_item, parent, false))
        }
        else if (viewType == VIEW_TYPE_OBSERVE_LIST){
            return ObserveList_ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.observe_list_parent_item, parent, false)
            )
        }
        else {
            return Poster_ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.poster_item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_SEARCH_BAR) {
            (holder as Searchbar_ViewHolder).bind(position)
        }
        else if (list[position].viewType == VIEW_TYPE_OPPORTUNITY){
            (holder as Opportunity_ViewHolder).bind(position)
        }
        else if (list[position].viewType == VIEW_TYPE_OBSERVE_LIST){
            (holder as ObserveList_ViewHolder).bind(position)
        }
        else {
            (holder as Poster_ViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}