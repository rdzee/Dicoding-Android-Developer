package com.example.bookit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book (
    val photo: Int,
    val title: String,
    val author: String,
    val description: String,
    val price: String,
    val category: String,
    val publisher: String,
    val rank: String,
    val sold: String,
    val page: String,
): Parcelable