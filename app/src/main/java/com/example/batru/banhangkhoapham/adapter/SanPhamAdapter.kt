package com.example.batru.banhangkhoapham.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.model.SanPham
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class SanPhamAdapter(dsSanPham: ArrayList<SanPham>, context: Context) :
        RecyclerView.Adapter<SanPhamAdapter.ItemHolder>() {
    var dsSanPham: ArrayList<SanPham>
    var context: Context

    init {
        this.dsSanPham = dsSanPham
        this.context = context
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val sanpham: SanPham = dsSanPham[position]
        holder.tvTenSP.text = sanpham.getName()
        val decimalFormat: DecimalFormat = DecimalFormat("###,###,###")
        holder.tvGiaSP.text = "Giá: ${decimalFormat.format(sanpham.getPrice())} Đ"
        Picasso.with(context).load(sanpham.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.failed)
                .into(holder.imgHinhSP)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder {
        val view: View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_sanpham_moi_nhat, parent, false)
        val holder = ItemHolder(view)
        return holder
    }

    override fun getItemCount(): Int = dsSanPham.size

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgHinhSP: ImageView
        var tvTenSP: TextView
        var tvGiaSP: TextView

        init {
            imgHinhSP = view.findViewById(R.id.imgSanPham)
            tvTenSP = view.findViewById(R.id.tvTenSP)
            tvGiaSP = view.findViewById(R.id.tvGiaSP)
        }
    }
}