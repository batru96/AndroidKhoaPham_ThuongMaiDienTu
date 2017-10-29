package com.example.batru.banhangkhoapham.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.batru.banhangkhoapham.R
import com.example.batru.banhangkhoapham.adapter.MobileAdapter
import com.example.batru.banhangkhoapham.model.SanPham
import com.example.batru.banhangkhoapham.util.StaticClass
import kotlinx.android.synthetic.main.activity_mobile.*
import org.json.JSONObject

class MobileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)

        initControls()
    }

    private fun initControls() {
        val dsProduct: ArrayList<SanPham> = arrayListOf()
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(StaticClass.urlSanPhamDienThoai,
                Response.Listener { response ->
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
                    }
                }, Response.ErrorListener { error ->
            Log.d("Volley", error.message)
        })
        queue.add(request)

        val adapter = MobileAdapter(this, dsProduct)
        lvMobile.adapter = adapter
    }
}
