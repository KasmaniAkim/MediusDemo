package com.demo.medius.data.local

data class Resource<out T>(
    val status: Status, val data: T?, val message: String? = null, val error: Exception? = null
) {
    enum class Status {
        SUCCESS,
        OFFLINE_ERROR,
        ERROR,
        EMPTY,
        LOADING
    }

    companion object {
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY, null)
        }

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> success(id: String): Resource<T> {
            return Resource(Status.SUCCESS, null, id)
        }

        fun <T> success(): Resource<T> {
            return Resource(Status.SUCCESS, null)
        }

        fun <T> error(
            error: Exception? = null,
            message: String? = null,
            data: T? = null
        ): Resource<T> {
            return Resource(Status.ERROR, data, message, error = error)
        }

        fun <T> offlineError(message: String? = null, data: T? = null): Resource<T> {
            return Resource(Status.OFFLINE_ERROR, data, message)
        }
    }
}

data class Resource2<out T>(
    val status: Status, val data: T?, val message: String? = null, val error: Exception? = null
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
    companion object {
        fun <T> loading(data: T? = null): Resource2<T> {
            return Resource2(Status.LOADING, data, null)
        }
        fun <T> success(data: T): Resource2<T> {
            return Resource2(Status.SUCCESS, data, null)
        }
        fun <T> error(error: Exception? = null, message: String? = null, data: T? = null): Resource2<T> {
            return Resource2(Status.ERROR, data, message, error = error)
        }

    }
}
