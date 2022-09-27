package com.hotdealnoti.notification.dto

import java.sql.Timestamp

class NotificationDto {
    companion object {


        data class PostKeywordRequest(
            val keyword: String
        )

        data class GetKeywordsResponse(
            val keywords: List<NotificationKeyword>
        )

        data class GetNotificationsResponse(
            val notifications: List<NotificationKeyword>
        )


        data class NotificationKeyword(
            val keywordNotificationId: Int,
            val keywordNotificationBody: String,
            val keywordNotificationTime: Timestamp
        )


        data class NotificationResponseDto(
            val notificationId: Int,
            val notificationTime: Timestamp,
            val notificationType: String,
            val notificationItemId: Int,
            val accountId: Int,
            val notificationTitle: String,
            val notificationBody: String,
            val isRead: Boolean
        )


        data class HotDealPreview(
            val hotDealId: Long,
            val title: String,
            val originalPrice: Int,
            val discountPrice: Int,
            val discountRate: Int,
            val link: String,
            val uploadTime: Timestamp,
            val viewCount: Int,
            val sourceSite: String,
            val productId: Long,
            val modelName: String,
            val manufacturer: String,
            val productPurpose: String,
            val isDelete: Boolean
        )


    }
}