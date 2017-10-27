package com.example.batru.banhangkhoapham.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.model.LoaiSanPham
import com.squareup.picasso.Picasso

class LoaiSanPhamAdapter(dsLoaiSP: ArrayList<LoaiSanPham>, context: Context) : BaseAdapter() {

    var dsLoaiSP: ArrayList<LoaiSanPham>
    var context: Context

    init {
        this.dsLoaiSP = dsLoaiSP
        this.context = context
    }

    override fun getItem(position: Int): Any = dsLoaiSP[position]

    override fun getItemId(id: Int): Long = id.toLong()

    override fun getCount(): Int = dsLoaiSP.size

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val view: View?
        val holder: ViewHolder
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_loai_san_pham, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        val loaiSP: LoaiSanPham = dsLoaiSP[position]
        holder.tvLoaiSP.text = loaiSP.getName()
        Picasso.with(context).load(loaiSP.getImageUrl())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.failed)
                .into(holder.imgLoaiSP)

        return view!!
    }

    class ViewHolder(row: View) {
        var tvLoaiSP: TextView
        var imgLoaiSP: ImageView

        init {
            tvLoaiSP = row.findViewById(R.id.tvLoaiSP)
            imgLoaiSP = row.findViewById(R.id.imgLoaiSP)
        }
    }

}