package com.example.batru.banhangkhoapham.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.model.SanPham
import com.example.batru.banhangkhoapham.util.StaticClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initControls()
    }

    private fun initControls() {
        setUpToolbar()


        val sp = intent.getSerializableExtra("PRODUCT") as SanPham

        Picasso.with(this).load(sp.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.failed)
                .into(imgProductDetail)
        tvProductName.text = sp.getName()
        tvDetailDescription.text = sp.getDescription()
        tvProductPrice.text = StaticClass.formatMoney(sp.getPrice())
    }

    private fun setUpToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
