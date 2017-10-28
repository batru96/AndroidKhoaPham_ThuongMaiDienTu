package com.example.batru.banhangkhoapham.model

class SanPham(id: Int, name: String, price: Int, image: String, description: String, idType: Int) {
    private var id: Int
    private var name: String
    private var price: Int
    private var image: String
    private var description: String
    private var idType: Int

    init {
        this.id = id
        this.name = name
        this.price = price
        this.image = image
        this.description = description
        this.idType = idType
    }

    fun getId(): Int = this.id
    fun getName(): String = this.name
    fun getPrice(): Int = this.price
    fun getImage(): String = this.image
    fun getDescription(): String = this.description
    fun getIdType(): Int = this.idType
}