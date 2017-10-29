package com.example.batru.banhangkhoapham.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.model.SanPham
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MobileAdapter(private var context: Context, private var dsProduct: ArrayList<SanPham>) : BaseAdapter() {
    override fun getItem(p0: Int): Any = dsProduct[p0]

    override fun getItemId(p0: Int): Long = dsProduct[p0].getId().toLong()

    override fun getCount(): Int = dsProduct.size

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        val view: View?
        val holder: ViewHolder

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_dien_thoai, null)
            holder = MobileAdapter.ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        val sanPham = dsProduct[position]
        holder.tvName.text = sanPham.getName()
        val decimalFormat = DecimalFormat("###,###,###")
        holder.tvPrice.text =  "${decimalFormat.format(sanPham.getPrice())} D"
        holder.tvDescription.text = sanPham.getDescription()
        Picasso.with(context).load(sanPham.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.failed)
                .into(holder.imgSanPham)
        return view!!
    }

    class ViewHolder(row: View) {
        var imgSanPham: ImageView
        var tvName: TextView
        var tvPrice: TextView
        var tvDescription: TextView

        init {
            imgSanPham = row.findViewById(R.id.imgMobile)
            tvName = row.findViewById(R.id.tvName)
            tvPrice = row.findViewById(R.id.tvPrice)
            tvDescription = row.findViewById(R.id.tvDescription)
        }
    }
}