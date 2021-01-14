package com.example.dgsw.maskinfo.repository

import com.example.dgsw.maskinfo.model.StoreInfo
import retrofit2.Call
import retrofit2.http.GET

interface MaskService {
    companion object {
        const val BASE_URL: String =
            "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94"
    }

    @GET("sample.json")
    fun fetchStoreInfo() : Call<StoreInfo>
}