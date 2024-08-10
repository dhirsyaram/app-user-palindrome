package com.kuliah.apppalindromeuser.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kuliah.apppalindromeuser.R
import com.kuliah.apppalindromeuser.data.response.DataItem
import com.kuliah.apppalindromeuser.databinding.ItemUserListBinding

class UserAdapter(private val onItemClick: (String) -> Unit) :
    PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: DataItem, onItemClick: (String) -> Unit) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(dataItem.avatar)
                    .apply(
                        RequestOptions
                            .centerCropTransform()
                            .placeholder(R.drawable.ic_refresh)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageUser)
                "${dataItem.firstName} ${dataItem.lastName}".also { tvUsername.text = it }
                tvEmail.text = dataItem.email

                itemView.setOnClickListener {
                    val username = "${dataItem.firstName} ${dataItem.lastName}"
                    onItemClick(username)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user, onItemClick)
        }
    }

    companion object {
        const val USERNAME = "username"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
