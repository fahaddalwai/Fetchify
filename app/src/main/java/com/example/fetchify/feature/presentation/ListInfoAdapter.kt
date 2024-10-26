package com.example.fetchify.feature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchify.databinding.ListItemBinding
import com.example.fetchify.feature.domain.model.ListInfo

class ListInfoAdapter :
    ListAdapter<ListInfo, ListInfoAdapter.ListInfoViewHolder>(ListInfoDiffCallback) {

    companion object ListInfoDiffCallback : DiffUtil.ItemCallback<ListInfo>() {
        override fun areItemsTheSame(oldItem: ListInfo, newItem: ListInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListInfo, newItem: ListInfo): Boolean {
            return oldItem == newItem
        }
    }

    inner class ListInfoViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListInfo) {
            binding.listItem = item
            binding.executePendingBindings() // Updates the views
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInfoViewHolder {
        return ListInfoViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListInfoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
