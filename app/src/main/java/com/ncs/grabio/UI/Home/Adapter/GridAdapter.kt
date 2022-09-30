package com.ncs.grabio.UI.Home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ncs.grabio.R
import com.ncs.grabio.UI.DataHolder


/**
 * Created by Alok Ranjan on 01-10-2022
 * Project : NCS Grab.io
 * Description :
 * Github : https://github.com/arpitmx
 **/
class GridAdapter(context: Context, courseModelArrayList: ArrayList<DHolder.DataGrid>) :
    ArrayAdapter<DHolder.DataGrid?>(context, 0, courseModelArrayList as List<DHolder.DataGrid?>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(R.layout.opportunities_grid_item_element, parent, false)
        }

        val courseModel: DHolder.DataGrid? = getItem(position)
        val courseTV = listitemView!!.findViewById<TextView>(R.id.idTVCourse)
        val courseIV = listitemView.findViewById<ImageView>(R.id.idIVcourse)

        courseTV.setText(courseModel!!.oppType)
        //courseIV.setImageResource(courseModel.imgid)
        return listitemView
    }
}