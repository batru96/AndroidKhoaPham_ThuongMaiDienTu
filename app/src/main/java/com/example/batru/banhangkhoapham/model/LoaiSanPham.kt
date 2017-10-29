package com.example.batru.banhangkhoapham.model

class LoaiSanPham(private var id: Int = -1,private var name: String,private var imageUrl: String) {
    fun getId(): Int = this.id
    fun getName(): String = this.name
    fun getImageUrl(): String = this.imageUrl
}