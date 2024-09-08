package com.example.atlys.base

import com.example.atlys.BaseApplication
import retrofit2.Response
import java.net.UnknownHostException

open class BaseRepository {
    suspend inline fun <reified TYPE, reified M> networkCall(
        crossinline apiCall: suspend () -> Response<TYPE>,
        crossinline mapper: ((TYPE) -> M)
    ): Resource<M> {
        return try {
            val response = apiCall()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if (body is BaseModel) {
                    val message = body.message.takeIf { it != null } ?: response.message()
                    Resource.Success(data = mapper(body), msg = message)
                } else {
                    Resource.Success(data = mapper(body), msg = response.message())
                }
            } else {
                val errorBodyStr = response.errorBody()?.string()
                val errorBody = BaseApplication.gsonProvider.getGson().fromJson(errorBodyStr, BaseModel::class.java)
                val message = if (!errorBody.message.isNullOrEmpty()) {
                    errorBody.message
                } else {
                    response.message()
                }
                Resource.Error(error = ResourceError.ApiError, msg = message)
            }
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                Resource.Error(ResourceError.ConnectionError(e), e.message)
            } else {
                Resource.Error(ResourceError.OtherError(e), e.message)
            }
        }
    }
}