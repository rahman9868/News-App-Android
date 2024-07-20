package com.example.mycafeapp.entity

data class Cafe(
    val id: Int,
    val address: String,
    val name: String,
    val location: String,
    val openTime: String,
    val closeTime:String,
    val rate: Double,
    val countRate: Int,
    val type: String,
    val picture: Int
)

data class Category(
    val image : Int,
    val name : String
)