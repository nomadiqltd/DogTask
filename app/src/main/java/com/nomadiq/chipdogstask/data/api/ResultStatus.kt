package com.nomadiq.chipdogstask.data.api

/**
 * @author Michael Akakpo
 * A generic class that contains data and status about incoming API response data from [Dog Ceo Api] .
 *
 * [Data] - Success 200 range responses
 * [Empty] - Empty response
 * [Error] - General error capture
 * [NetworkError] - Network, connectivity issues
 */

/** A sealed class representing preset [ResultStatus] types to facilitate error management **/
sealed class ResultStatus<T> {
    data class Data<T>(val result: T) : ResultStatus<T>()
    data class Empty<T>(val status: Int) : ResultStatus<T>()
    data class Error<T>(val error: String) : ResultStatus<T>()
    data class NetworkError<T>(val message: String?) : ResultStatus<T>()
}