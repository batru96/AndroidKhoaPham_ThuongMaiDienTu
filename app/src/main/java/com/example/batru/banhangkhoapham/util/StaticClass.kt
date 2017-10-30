package com.example.batru.banhangkhoapham.util

import android.content.Context
import android.widget.Toast
import java.text.DecimalFormat

class StaticClass {
    companion object {
        private val localhost = "192.168.20.119"
        val urlLoaiSanPham = "http://$localhost/ThuongMaiDienTu/getloaisp.php"
        val imageUrl = "http://$localhost/ThuongMaiDienTu/images/"
        var urlSanPhamMoiNhat = "http://$localhost/ThuongMaiDienTu/getsanphammoinhat.php"
        var urlSanPhamDienThoai = "http://$localhost/ThuongMaiDienTu/getsanpham.php?"

        fun shortToast(context: Context, message: String) =
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        fun formatMoney(money: Int): String {
            val fomatter = DecimalFormat("###,###,###")
            return fomatter.format(money) + " Đ"
        }
    }
}