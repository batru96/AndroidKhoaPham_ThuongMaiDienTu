package com.example.batru.banhangkhoapham.util

import android.content.Context
import android.widget.Toast

class StaticClass {
    companion object {
        val localhost = "192.168.0.106"
        val urlLoaiSanPham = "http://$localhost/ThuongMaiDienTu/getloaisp.php"
        var urlSanPhamMoiNhat = "http://$localhost/ThuongMaiDienTu/getsanphammoinhat.php"
        fun shortToast(context: Context, message: String) =
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}