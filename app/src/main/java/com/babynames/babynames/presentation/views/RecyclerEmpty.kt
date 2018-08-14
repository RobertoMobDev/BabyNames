package com.babynames.babynames.presentation.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

class RecyclerEmpty : RecyclerView {


    private var mEmptyView: View? = null

    private val emptyObserver = object : RecyclerView.AdapterDataObserver() {


        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context)


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun setAdapter(adapter: Adapter<*>?) {

        getAdapter()?.unregisterAdapterDataObserver(emptyObserver)

        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(emptyObserver)

    }

    fun checkIfEmpty() {
        if (mEmptyView != null) {
            if (adapter.itemCount > 0) {
                mEmptyView!!.visibility = View.GONE
                this@RecyclerEmpty.visibility = View.VISIBLE
            } else {
                mEmptyView!!.visibility = View.VISIBLE
                this@RecyclerEmpty.visibility = View.GONE
            }
        }
    }

    fun setEmptyView(emptyView: View) {
        mEmptyView = emptyView
        checkIfEmpty()
    }
}