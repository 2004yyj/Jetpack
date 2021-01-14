package com.example.dgsw.maskinfo.model

import com.squareup.moshi.Json

data class StoreInfo (
        val count : Int,
        val stores : List<Store>
)