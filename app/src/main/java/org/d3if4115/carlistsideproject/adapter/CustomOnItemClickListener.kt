package org.d3if4115.carlistsideproject.adapter

import android.view.View


class CustomOnItemClickListener(
    private var position: Int,
    private var onItemClickCallback: OnItemClickCallback?
) : View.OnClickListener {

    override fun onClick(view: View?) {
        onItemClickCallback!!.onItemClicked(view, position)
    }

    interface OnItemClickCallback {
        fun onItemClicked(view: View?, position: Int)
    }
}
