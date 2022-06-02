package com.example.mynewsapp.response

data class Response<out T>(val status: Status?, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Response<T> {
            return Response(Status.ERROR, data, msg)
        }

        fun <T> showLoading(data: T?): Response<T> {
            return Response(Status.SHOWLOADING, data, null)
        }

        fun <T> hideLoading(data: T?): Response<T> {
            return Response(Status.HIDELOADING, data, null)
        }

        fun <T> empty(msg: String, data: T?): Response<T> {
            return Response(Status.EMPTY, data, msg)
        }
    }
}