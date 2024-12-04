package com.example.paging.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseResponse(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val characters: List<Character>? = null
):Parcelable