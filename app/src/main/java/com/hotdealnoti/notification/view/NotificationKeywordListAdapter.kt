package com.hotdealnoti.notification.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hotdealnoti.databinding.NotificationKeywordItemBinding
import com.hotdealnoti.notification.dto.NotificationDto


class NotificationKeywordListAdapter(val onClickDeleteKeywordButton: (Int) -> Unit) : ListAdapter<NotificationDto.Companion.NotificationKeyword, NotificationKeywordListAdapter.NotificationKeywordItemViewHolder>(
    NotificationKeywordDiffCallback()
){

    inner class NotificationKeywordItemViewHolder(
        val notificationKeywordItemBinding: NotificationKeywordItemBinding
    ) : RecyclerView.ViewHolder(notificationKeywordItemBinding.root)

    class NotificationKeywordDiffCallback : DiffUtil.ItemCallback<NotificationDto.Companion.NotificationKeyword>() {
        override fun areItemsTheSame(
            oldItem: NotificationDto.Companion.NotificationKeyword,
            newItem: NotificationDto.Companion.NotificationKeyword,
        ): Boolean {
            return oldItem.keywordNotificationId == newItem.keywordNotificationId

        }

        override fun areContentsTheSame(
            oldItem: NotificationDto.Companion.NotificationKeyword,
            newItem: NotificationDto.Companion.NotificationKeyword,
        ): Boolean {
            return oldItem.keywordNotificationId == newItem.keywordNotificationId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationKeywordItemViewHolder {
        return NotificationKeywordItemViewHolder(NotificationKeywordItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: NotificationKeywordItemViewHolder, position: Int) {

        val notificationKeyword = getItem(position)
        Log.d("item",notificationKeyword.toString())
        holder.notificationKeywordItemBinding.notificationKeyword= notificationKeyword
        holder.notificationKeywordItemBinding.deleteKeywordB.setOnClickListener {
            onClickDeleteKeywordButton(notificationKeyword.keywordNotificationId)
        }
    }
}