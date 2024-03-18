package com.adit.swiftpedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Album(
    val albumName : String,
    val releaseDate : String,
    val description : String,
    val photo: Int
) : Parcelable
