package com.example.dgsw.maskinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dgsw.maskinfo.model.Store
import com.example.dgsw.maskinfo.model.StoreInfo
import com.example.dgsw.maskinfo.repository.MaskService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl(MaskService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(MaskService::class.java)

        val storeInfoCall = service.fetchStoreInfo()

        val list : ArrayList<Store> = ArrayList()

        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val recyclerAdapter = RecyclerAdapter()
        recyclerView.adapter = recyclerAdapter

        storeInfoCall.enqueue(object : Callback<StoreInfo> {
            override fun onResponse(call: Call<StoreInfo>, response: Response<StoreInfo>) {
                list.addAll(response.body()!!.stores)
                recyclerAdapter.updateData(list)
            }

            override fun onFailure(call: Call<StoreInfo>, t: Throwable) {

            }

        })

    }
}