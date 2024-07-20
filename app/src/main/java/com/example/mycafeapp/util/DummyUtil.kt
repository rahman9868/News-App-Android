package com.example.mycafeapp.util

import com.example.mycafeapp.R
import com.example.mycafeapp.entity.Cafe
import com.example.mycafeapp.entity.Category

object DummyUtil {

    private val listCafe: MutableList<Cafe> = mutableListOf(
        Cafe(
            id = 1,
            name = "Tanatap Cofee",
            location = "Ampera",
            address = "Jl. Ampera Jaya No 1, Jakarta Selatan",
            rate = 4.8,
            countRate = 2511,
            type = "Coffee Shop",
            openTime = "07.00",
            closeTime = "23.00",
            picture = R.drawable.pic_cafe_1
        ),
        Cafe(
            id = 2,
            name = "Tanatap Cofee",
            location = "Ampera",
            address = "Jl. Ampera Jaya No 1, Jakarta Selatan",
            rate = 4.8,
            countRate = 2511,
            type = "Coffee Shop",
            openTime = "07.00",
            closeTime = "23.00",
            picture = R.drawable.pic_cafe_1
        ),
        Cafe(
            id = 3,
            name = "Tanatap Cofee",
            location = "Ampera",
            address = "Jl. Ampera Jaya No 1, Jakarta Selatan",
            rate = 4.8,
            countRate = 2511,
            type = "Coffee Shop",
            openTime = "07.00",
            closeTime = "23.00",
            picture = R.drawable.pic_cafe_1
        ),
        Cafe(
            id = 4,
            name = "Tanatap Cofee",
            location = "Ampera",
            address = "Jl. Ampera Jaya No 1, Jakarta Selatan",
            rate = 4.8,
            countRate = 2511,
            type = "Coffee Shop",
            openTime = "07.00",
            closeTime = "23.00",
            picture = R.drawable.pic_cafe_1
        ),
        Cafe(
            id = 5,
            name = "Tanatap Cofee",
            location = "Ampera",
            address = "Jl. Ampera Jaya No 1, Jakarta Selatan",
            rate = 4.8,
            countRate = 2511,
            type = "Coffee Shop",
            openTime = "07.00",
            closeTime = "23.00",
            picture = R.drawable.pic_cafe_1
        )
    )
    fun getListCafe(): List<Cafe>{
        return listCafe
    }

    fun recommendedCafe() =  listCafe.first()

    fun getCategory(): List<Category> {
        return listOf(
            Category(R.drawable.cat_nearby, "Nearby"),
            Category(R.drawable.cat_coffe, "Coffee"),
            Category(R.drawable.cat_desert, "Desert"),
            Category(R.drawable.cat_healthy, "Healthy"),
            Category(R.drawable.cat_instant, "Instant"),
            Category(R.drawable.cat_street_food, "Street Food")
        )
    }
}