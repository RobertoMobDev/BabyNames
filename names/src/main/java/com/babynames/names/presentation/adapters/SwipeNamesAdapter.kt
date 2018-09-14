package com.babynames.names.presentation.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.babynames.names.R
import com.babynames.names.domain.entities.Name
import kotlinx.android.synthetic.main.name_view.view.*
import org.jetbrains.anko.backgroundColor

class SwipeNamesAdapter : BaseAdapter() {

    private val namesList: MutableList<Name> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mConvertView: View = LayoutInflater.from(parent?.context).inflate(R.layout.name_view, parent, false)
        val nameItem = this.namesList[position]

        val textName = mConvertView.text_option_name
        val textMeaning = mConvertView.text_option_meaning
        val textOrigin = mConvertView.text_option_origin
        val nameViewLayout = mConvertView.layout_name_view
        val nameTextLayout = mConvertView.layout_text_name

        when (nameItem.gender) {
            "male" -> {
                nameViewLayout.backgroundColor = parent?.context?.getColor(R.color.colorBlueBoyAlpha)!!
                nameTextLayout.backgroundColor = parent.context?.getColor(R.color.colorBlueBoy)!!
            }
            "female" -> {
                nameViewLayout.backgroundColor = parent?.context?.getColor(R.color.colorPinkGirlAlpha)!!
                nameTextLayout.backgroundColor = parent.context?.getColor(R.color.colorPinkGirl)!!
            }
            else -> {
                nameViewLayout.backgroundColor = parent?.context?.getColor(R.color.colorSurpriseAlpha)!!
                nameTextLayout.backgroundColor = parent.context?.getColor(R.color.colorSurprise)!!
            }
        }

        textName.text = nameItem.name
        textMeaning.text = nameItem.meaning
        textOrigin.text = nameItem.origin

        return mConvertView
    }

    override fun getItem(position: Int): Any {
        return namesList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = this.namesList.size

    fun updateNamesList(list: List<Name>) {
        this.namesList.clear()
        this.namesList.addAll(list)
        notifyDataSetChanged()
    }
}