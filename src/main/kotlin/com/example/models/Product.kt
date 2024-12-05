package com.example.models

import kotlinx.serialization.Serializable

enum class ProductType {
    Type1, Type2, Type3
}

@Serializable
data class Product(
    val id: Int,
    val name: String = "",
    val type: ProductType,
    val imageId: Int,
    val price: Double = 0.0,
    val description: String = ""
)