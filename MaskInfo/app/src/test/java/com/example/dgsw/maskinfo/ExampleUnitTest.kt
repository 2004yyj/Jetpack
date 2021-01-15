package com.example.dgsw.maskinfo

import android.util.Log
import com.example.dgsw.maskinfo.model.StoreInfo
import com.example.dgsw.maskinfo.repository.MaskService
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofit_test() {
        val retrofit = Retrofit.Builder()
                .baseUrl(MaskService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(MaskService::class.java)

        val storeInfoCall = service.fetchStoreInfo()

        Log.e("TAG", "onResponse: ${storeInfoCall.execute().body()}" )

//        storeInfoCall.enqueue(object : Callback<StoreInfo> {
//            override fun onResponse(call: Call<StoreInfo>, response: Response<StoreInfo>) {
//                Log.e("TAG", "onResponse: ${response.body()!!.stores}" )
//            }
//
//            override fun onFailure(call: Call<StoreInfo>, t: Throwable) {
//
//            }
//
//        })

    }
}