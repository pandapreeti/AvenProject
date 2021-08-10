package com.example.avendemoapp.models

import com.google.gson.annotations.SerializedName

data class Organisation(
    @SerializedName("avatar_url")
    val image:String?,
    @SerializedName("login")
    val name:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("url")
    val userUrl:String?
)
