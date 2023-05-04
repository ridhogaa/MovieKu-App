package com.ergea.moviekuapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.ergea.moviekuapp.data.model.ResultsItem
import com.ergea.moviekuapp.databinding.ItemListBinding


/**
 * @Author: ridhogymnastiar
 * Github: https://github.com/ridhogaa
 */

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(
            oldItem: ResultsItem,
            newItem: ResultsItem
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResultsItem,
            newItem: ResultsItem
        ): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder =
        ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem) {
            val imageUrl = "https://image.tmdb.org/t/p/w500"
            binding.name.text = item.title
            Glide.with(itemView).load(imageUrl + item.backdropPath).into(binding.photo)
            binding.root.setOnClickListener {
                val pDialog = SweetAlertDialog(itemView.context, SweetAlertDialog.SUCCESS_TYPE)
                pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
                pDialog.titleText = "Success"
                pDialog.confirmText = "OK"
                pDialog.cancelText = "CANCEL"
                pDialog.show()
            }
        }
    }
}