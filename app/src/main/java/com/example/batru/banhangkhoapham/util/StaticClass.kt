package com.example.batru.banhangkhoapham.util

import android.content.Context
import android.widget.Toast

class StaticClass {
    companion object {
        val localhost = "10.10.2.58"
        val urlLoaiSanPham = "http://$localhost/ThuongMaiDienTu/getloaisp.php"
        val imageUrl = "http://$localhost/ThuongMaiDienTu/images/"
        var urlSanPhamMoiNhat = "http://$localhost/ThuongMaiDienTu/getsanphammoinhat.php"
        var urlSanPhamDienThoai = "http://$localhost/ThuongMaiDienTu/getsanpham.php?"

        fun shortToast(context: Context, message: String) =
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}