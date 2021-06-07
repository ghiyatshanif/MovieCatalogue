package com.ghiyatshanif.moviecatalogue.core.utils.network

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("message")
    var message: String = ""
)