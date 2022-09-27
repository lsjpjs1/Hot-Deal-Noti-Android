package com.hotdealnoti.notification.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hotdealnoti.common.LocaleUtil
import com.hotdealnoti.databinding.NotificationItemBinding
import com.hotdealnoti.notification.dto.NotificationDto
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class NotificationListAdapter(val onNotificationClick: (NotificationDto.Companion.NotificationResponseDto) -> Unit) : ListAdapter<NotificationDto.Companion.NotificationResponseDto, NotificationListAdapter.NotificationItemViewHolder>(
    NotificationListAdapter.NotificationDiffCallback()
) {

    inner class NotificationItemViewHolder(
        val notificationItemBinding: NotificationItemBinding
    ) : RecyclerView.ViewHolder(notificationItemBinding.root)

    class NotificationDiffCallback : DiffUtil.ItemCallback<NotificationDto.Companion.NotificationResponseDto>() {
        override fun areItemsTheSame(
            oldItem: NotificationDto.Companion.NotificationResponseDto,
            newItem: NotificationDto.Companion.NotificationResponseDto,
        ): Boolean {
            return oldItem.notificationId == newItem.notificationId

        }

        override fun areContentsTheSame(
            oldItem: NotificationDto.Companion.NotificationResponseDto,
            newItem: NotificationDto.Companion.NotificationResponseDto,
        ): Boolean {
            return oldItem.notificationId == newItem.notificationId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationItemViewHolder {
        return NotificationItemViewHolder(NotificationItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: NotificationItemViewHolder, position: Int) {

        val notificationResponseDto = getItem(position)
        val timeago = PrettyTime(LocaleUtil().getSystemLocale(holder.notificationItemBinding.root.context)).format(
            Date(notificationResponseDto.notificationTime.time)
        ).replace(" ago","")
        holder.notificationItemBinding.notificationResponseDto = notificationResponseDto
        holder.notificationItemBinding.notificationTimeAgo = timeago
        holder.notificationItemBinding.notificationItemCL.setOnClickListener {
            onNotificationClick(notificationResponseDto)
        }

    }
}