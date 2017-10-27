package com.example.batru.banhangkhoapham.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.batru.banhangkhoapham.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControls()
    }

    fun initControls() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.START)
        }

        // setup view flipper
        val dsQuangCao: ArrayList<String> = arrayListOf()
        dsQuangCao.add("https://pisces.bbystatic.com/BestBuy_US/store/ee/2016/mob/pr/153114-dept-page/cell_phones_unlocked.jpg;maxHeight=288;maxWidth=520")
        dsQuangCao.add("https://cdn3.tgdd.vn/Products/Images/44/85983/lenovo-ideapad-110-15ibr-80t700bkvn-den-h-450x300-450x300.jpg")

        for(i in dsQuangCao) {
            val imageView = ImageView(this)
            Picasso.with(this).load(i).into(imageView)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            viewflipper.addView(imageView)
        }
        viewflipper.setFlipInterval(5000)
        viewflipper.isAutoStart = true

        val animation_slide_in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        val animation_slide_out = AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
        viewflipper.setInAnimation(animation_slide_in)
        viewflipper.setOutAnimation(animation_slide_out)
    }
}
