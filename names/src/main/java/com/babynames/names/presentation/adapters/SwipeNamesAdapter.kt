package com.babynames.names.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.babynames.names.R
import kotlinx.android.synthetic.main.name_view.view.*

class SwipeNamesAdapter(private val namesList: List<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var mConvertView = convertView

        mConvertView = LayoutInflater.from(parent?.context).inflate(R.layout.name_view, parent, false)

        val textHeader = mConvertView.text_option_meaning

        textHeader.text = this.namesList[position]

        return mConvertView
    }

    override fun getItem(position: Int): Any {
        return namesList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = this.namesList.size
}