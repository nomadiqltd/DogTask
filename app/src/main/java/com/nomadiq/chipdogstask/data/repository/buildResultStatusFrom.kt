package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.data.api.ResultStatus
import retrofit2.Response

/**
 *
 *  @author - Michael Akakpo
 *
 * Check the response from the [Dog API] and return a [ResultStatus]
 * to provide a fixed predetermined set of possible outcomes on the status of the network request
 */
fun <T> buildResultStatusFrom(response: Response<T>): ResultStatus<T> {
    if (response.isSuccessful) {
        response.body()?.let {
            return ResultStatus.Data(it)
        } ?: return ResultStatus.Empty(response.code())
    } else {
        return ResultStatus.Error(
            error = "errorCode :: ${response.code()} ==> ${response.message()}"
        )
    }
}