package com.example.batru.banhangkhoapham.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ImageView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.R.id.lvLeftMenu
import com.example.batru.banhangkhoapham.adapter.LoaiSanPhamAdapter
import com.example.batru.banhangkhoapham.adapter.SanPhamAdapter
import com.example.batru.banhangkhoapham.model.LoaiSanPham
import com.example.batru.banhangkhoapham.model.SanPham
import com.example.batru.banhangkhoapham.util.StaticClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControls()
    }

    private fun initControls() {
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

        for (i in dsQuangCao) {
            val imageView = ImageView(this)
            Picasso.with(this).load(i).into(imageView)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            viewflipper.addView(imageView)
        }
        viewflipper.setFlipInterval(5000)
        viewflipper.isAutoStart = true

        val animationSlideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        val animationSlideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
        viewflipper.inAnimation = animationSlideIn
        viewflipper.outAnimation = animationSlideOut

        val requestQueue: RequestQueue = Volley.newRequestQueue(this@MainActivity)
        loadLoaiSanPham(requestQueue)
        loadSanPhamMoiNhat(requestQueue)
    }

    private fun loadSanPhamMoiNhat(requestQueue: RequestQueue) {
        val dsSanPham: ArrayList<SanPham> = arrayListOf()
        val request = JsonArrayRequest(StaticClass.urlSanPhamMoiNhat,
                Response.Listener<JSONArray> { response ->
                    if (response.length() > 0) {
                        for (i in 0 until response.length()) {
                            val obj = response.getJSONObject(i)
                            val id = obj.getInt("id")
                            val name = obj.getString("tenSP")
                            val price = obj.getInt("giaSP")
                            val image = obj.getString("hinhAnh")
                            val description = obj.getString("moTa")
                            val idType = obj.getInt("idLoai")
                            dsSanPham.add(SanPham(
                                    id = id, name = name, price = price, image = image,
                                    description = description, idType = idType))
                        }
                    }
                }, Response.ErrorListener { error ->
            StaticClass.shortToast(this@MainActivity, error.message.toString())
        })
        requestQueue.add(request)
        val adapter = SanPhamAdapter(dsSanPham, this)
        rvSanPham.setHasFixedSize(true)
        rvSanPham.layoutManager = GridLayoutManager(this, 2)
        rvSanPham.adapter = adapter
    }

    private fun loadLoaiSanPham(requestQueue: RequestQueue) {
        val dsMenu: ArrayList<LoaiSanPham> = arrayListOf()
        val request = JsonArrayRequest(StaticClass.urlLoaiSanPham,
                Response.Listener<JSONArray> { response ->
                    if (response.length() > 0) {
                        for (i in 0 until response.length()) {
                            val obj = response.getJSONObject(i)
                            val id = obj.getInt("id")
                            val name = obj.getString("tenLoai")
                            val image = StaticClass.imageUrl + obj.getString("hinhAnh")
                            dsMenu.add(LoaiSanPham(id, name, image))
                        }
                    }
                    dsMenu.add(0, LoaiSanPham(name = "Trang chính", imageUrl = StaticClass.imageUrl + "home.ico"))
                    dsMenu.add(LoaiSanPham(name = "Liên hệ", imageUrl = StaticClass.imageUrl + "contact.ico"))
                    dsMenu.add(LoaiSanPham(name = "Thông tin", imageUrl = StaticClass.imageUrl + "info.png"))
                }, Response.ErrorListener { error ->
            StaticClass.shortToast(this@MainActivity, error.message.toString())
        })
        requestQueue.add(request)

        val adapter = LoaiSanPhamAdapter(dsMenu, this)
        lvLeftMenu.adapter = adapter

        lvLeftMenu.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val intent: Intent?
                when (position) {
                    1 -> intent = Intent(this@MainActivity, MobileActivity::class.java)
                    2 -> intent = Intent(this@MainActivity, MobileActivity::class.java)
                    3 -> intent = Intent(this@MainActivity, ContactActivity::class.java)
                    4 -> intent = Intent(this@MainActivity, InfomationActivity::class.java)
                    else -> intent = null
                }
                if (intent != null) {
                    val id = dsMenu[position].getId()
                    if (id != -1) intent.putExtra("IdLoaiSP", id)
                    startActivity(intent)
                } else {
                    drawerLayout.closeDrawer(Gravity.START)
                }
            }
        })
    }
}
