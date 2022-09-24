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


        data class NotificationKeyword(
            val keywordNotificationId: Int,
            val keywordNotificationBody: String,
            val keywordNotificationTime: Timestamp
        )

    }
}