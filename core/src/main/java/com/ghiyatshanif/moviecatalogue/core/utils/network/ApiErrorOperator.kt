package com.ghiyatshanif.moviecatalogue.core.utils.network

import com.ghiyatshanif.moviecatalogue.core.utils.rx.SingleApiErrorOperator
import com.google.gson.Gson

fun <T> singleApiError(): SingleApiErrorOperator<T> {
    return SingleApiErrorOperator(
        Gson()
    )
}