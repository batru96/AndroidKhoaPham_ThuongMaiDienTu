package com.example.batru.banhangkhoapham.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.adapter.MobileAdapter
import com.example.batru.banhangkhoapham.model.SanPham
import com.example.batru.banhangkhoapham.util.StaticClass
import kotlinx.android.synthetic.main.activity_mobile.*
import kotlinx.android.synthetic.main.processbar.*
import org.json.JSONObject

class MobileActivity : AppCompatActivity() {
    var queue: RequestQueue? = null
    val dsProduct: ArrayList<SanPham>
    val adapter: MobileAdapter
    var isLoading = false
    var limitData = false
    var footer: View? = null
    var page: Int = 1
    var idType: Int? = null
    val customHandler: CustomHanlder

    init {
        dsProduct = arrayListOf<SanPham>()
        adapter = MobileAdapter(this, dsProduct)
        customHandler = CustomHanlder()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)

        initControls()
    }

    private fun initControls() {
        queue = Volley.newRequestQueue(this)
        idType = intent.getIntExtra("IdLoaiSP", -1)
        getData(page, idType!!)
        lvMobile.adapter = adapter

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        footer = inflater.inflate(R.layout.processbar, null)

        loadMoreData()

        lvMobile.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val item = dsProduct[position]
                val intent: Intent = Intent(this@MobileActivity, ProductDetailActivity::class.java)
                intent.putExtra("PRODUCT", item)
                startActivity(intent)
            }
        })
    }

    private fun loadMoreData() {
        lvMobile.setOnScrollListener(object :AbsListView.OnScrollListener{
            override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {

            }

            override fun onScroll(absListView: AbsListView?, firstItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (absListView!!.lastVisiblePosition == totalItemCount - 1 && totalItemCount != 0 && !isLoading && !limitData) {
                    isLoading = true
                    val threadData = ThreadData()
                    threadData.start()
                }
            }
        })
    }

    private fun getData(page: Int, idLoaiSP: Int) {
        val url = "${StaticClass.urlSanPhamDienThoai}idType=$idLoaiSP&page=$page"
        val request = JsonArrayRequest(url, Response.Listener { response ->
            lvMobile.removeFooterView(footer)
            if (response.length() > 0) {
                for (i in 0 until response.length()) {
                    val jsonObj: JSONObject = response.getJSONObject(i)
                    val id = jsonObj.getInt("id")
                    val name = jsonObj.getString("name")
                    val image = jsonObj.getString("image")
                    val description = jsonObj.getString("description")
                    val idType = jsonObj.getInt("idType")
                    val price = jsonObj.getInt("price")
                    dsProduct.add(SanPham(id = id, name = name, price = price, image = image, description = description, idType = idType))
                }
                adapter.notifyDataSetChanged()
            } else {
                limitData = true
                lvMobile.removeFooterView(footer)
            }
        }, Response.ErrorListener { error ->
            Log.d("Volley", error.message)
        })
        queue!!.add(request)
    }

    @SuppressLint("HandlerLeak")
    inner class CustomHanlder: Handler() {
        override fun handleMessage(msg: Message?) {
            when(msg?.what) {
                0 -> lvMobile.addFooterView(footer)
                1 -> {
                    getData(++page, idType!!)
                    isLoading = false
                }
            }
            super.handleMessage(msg)
        }
    }

    inner class ThreadData: Thread() {
        override fun run() {
            customHandler.sendEmptyMessage(0)
            Thread.sleep(3000)
            val message: Message = customHandler.obtainMessage(1)
            customHandler.sendMessage(message)
            super.run()
        }
    }
}