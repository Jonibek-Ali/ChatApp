package com.alikom.mychat.messenger.model

import androidx.annotation.DrawableRes

sealed class ChatDetailModel {

    data class MyMessage(val message: String) : ChatDetailModel()

    data class FriendMessage(val mesage: String, @DrawableRes val avatar: Int) : ChatDetailModel()

    data class FriendImageMessage(
        @DrawableRes val avatar: Int,
        @DrawableRes val image: Int,
        val description: String,
        val link: String
    ) : ChatDetailModel()

}