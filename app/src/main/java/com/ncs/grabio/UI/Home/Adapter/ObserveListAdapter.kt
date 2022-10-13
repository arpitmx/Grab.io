package com.ncs.grabio.UI.Home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ncs.grabio.R


/**
 * Created by Alok Ranjan on 01-10-2022
 * Project : NCS Dr.Docs
 * Description :
 * Github : https://github.com/arpitmx
 **/
class ObserveListAdapter(context: Context, data: ArrayList<Dataholder>) : BaseAdapter() {
        var context: Context
        var data: ArrayList<Dataholder>


        override fun getCount(): Int {
            // TODO Auto-generated method stub
            return data.size
        }

        override fun getItem(position: Int): Any {
            // TODO Auto-generated method stub
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            // TODO Auto-generated method stub
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // TODO Auto-generated method stub
            var vi: View? = convertView
            if (vi == null) vi = inflater!!.inflate(R.layout.observe_list_item, null)
            val contestName = vi!!.findViewById(R.id.contest_name) as TextView
            val contestSite = vi.findViewById(R.id.contest_site) as TextView
            contestName.text = data[position].contestName
            contestSite.text = data[position].contestSite
            return vi
        }

        companion object {
            private var inflater: LayoutInflater? = null
        }

        init {
            // TODO Auto-generated constructor stub
            this.context = context
            this.data = data
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        }
    }
