package com.example.spinnergame.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.spinnergame.model.Result
import com.example.spinnergame.databinding.ResultItemBinding
import java.util.*


class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    class ResultViewHolder(val itemBinding: ResultItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback =
        object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.result == newItem.result &&
                        oldItem.time == newItem.time
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ResultItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentResult = differ.currentList[position]

        holder.itemBinding.resultTxt.text = currentResult.result
        holder.itemBinding.timeTxt.text = currentResult.time
        holder.itemBinding.timeTxt.text = currentResult.time

        holder.itemBinding.ibColor.setBackgroundColor(currentResult.color)



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}