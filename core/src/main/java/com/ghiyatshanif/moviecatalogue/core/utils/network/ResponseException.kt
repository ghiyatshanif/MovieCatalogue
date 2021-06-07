package com.ghiyatshanif.moviecatalogue.core.utils.network

open class ResponseException(open var response: retrofit2.Response<*>) : RuntimeException()