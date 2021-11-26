package com.ihsan.binarchallengechapter7.helper

import android.content.ClipData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.binarchallengechapter7.R
import com.ihsan.binarchallengechapter7.databinding.ItemHistoryBinding
import com.ihsan.binarchallengechapter7.model.DataItem

class HistoryAdapter(listHistory: ArrayList<DataItem>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var data = ArrayList<DataItem>()
    private val TAG = HistoryAdapter::class.java.simpleName

    init {
        data = listHistory
        Log.i(TAG, "data: $data")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val binding = ItemHistoryBinding.bind(ItemView)
        fun bind(dataItem: DataItem) {
            binding.tvMode.text = dataItem.mode
            binding.tvMessage.text = dataItem.message
        }
    }
}