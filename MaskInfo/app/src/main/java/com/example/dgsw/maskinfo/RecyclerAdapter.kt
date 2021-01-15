package com.example.dgsw.maskinfo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dgsw.maskinfo.model.Store

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var data = ArrayList<Store>()

    inner class ViewHolder constructor(parent : ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
    ) {
        val name = itemView.findViewById<TextView>(R.id.name_text_view)
        val addr = itemView.findViewById<TextView>(R.id.addr_text_view)
        val remain = itemView.findViewById<TextView>(R.id.remain_text_view)
        val count = itemView.findViewById<TextView>(R.id.count_text_view)
        val distance = itemView.findViewById<TextView>(R.id.dist_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    fun updateData(data : ArrayList<Store>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].let { item ->
            with(holder) {
                holder.name.text = item.name
                holder.addr.text = item.addr
                holder.addr.isSelected = true
                when(item.remain_stat) {
                    "few" -> {
                        holder.remain.text = "부족"
                        holder.count.text = "10개 이상"
                        holder.remain.setTextColor(Color.parseColor("#F2728C"))
                        holder.count.setTextColor(Color.parseColor("#F2728C"))
                    }
                    "some" -> {
                        holder.remain.text = "적음"
                        holder.count.text = "30개 이상"
                        holder.remain.setTextColor(Color.parseColor("#FFD400"))
                        holder.count.setTextColor(Color.parseColor("#FFD400"))
                    }
                    "break" -> {
                        holder.remain.text = "많음"
                        holder.count.text = "50개 이상"
                        holder.remain.setTextColor(Color.parseColor("#27AAE1"))
                        holder.count.setTextColor(Color.parseColor("#27AAE1"))
                    }
                    "plenty" -> {
                        holder.remain.text = "충분함"
                        holder.count.text = "100개 이상"
                        holder.remain.setTextColor(Color.parseColor("#80B463"))
                        holder.count.setTextColor(Color.parseColor("#80B463"))
                    }
                    else -> {
                        holder.remain.text = "없음"
                        holder.count.text = "0개"
                        holder.remain.setTextColor(Color.parseColor("#EF404A"))
                        holder.count.setTextColor(Color.parseColor("#EF404A"))
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}