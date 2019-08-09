package com.android.freelance.album.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import com.android.freelance.album.R
import com.android.freelance.album.data.db.entity.Albums
import kotlinx.android.synthetic.main.item_albums_list.view.*


class AlbumsAdapters(val album: List<Albums>) : RecyclerView.Adapter<AlbumsAdapters.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0!!.context)
        val row = layoutInflater.inflate(R.layout.item_albums_list, p0, false)
        return ViewHolder(row)
    }

    override fun getItemCount(): Int {
        return album.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(album[p1])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*val itemView = albumList*/

       /* private val main: TextView = itemView.findViewById(R.id.tvMain)
        private val detail: TextView = itemView.findViewById(R.id.tvDetail)*/

        fun bind(item: Albums) {
            itemView.tvMain.text = item.title
            itemView.tvDetail.text = item.artist
            itemView.setOnClickListener {}
        }
    }
}