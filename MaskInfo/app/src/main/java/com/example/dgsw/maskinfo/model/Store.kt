package com.example.dgsw.maskinfo.model

import com.squareup.moshi.Json

data class Store (
    val addr : String,
    val code : String,
    val createdAt : String,
    val lat : Double,
    val lng : Double,
    val name : String,
    val remainStat : String,
    val stockAt : String,
    val type : String,
)