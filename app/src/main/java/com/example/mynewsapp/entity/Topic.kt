package com.example.mynewsapp.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuggestedTopics(
    val image : Int,
    val title : String
): Parcelable