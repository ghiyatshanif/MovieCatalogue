package com.ghiyatshanif.moviecatalogue.core.utils.network

class ApiException(val apiError: ApiError, override var response: retrofit2.Response<*>) : ResponseException(response)