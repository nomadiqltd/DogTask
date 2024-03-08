package com.nomadiq.chipdogstask.data.mapper

/**
 * Generic Mapper used to map the response entity to a Domain object for transferring around objects within UI Layer */
interface Mapper<T, U> {
    fun map(value: T): U
}