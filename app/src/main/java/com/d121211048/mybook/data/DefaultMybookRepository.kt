package com.d121211048.mybook.data

import com.d121211048.mybook.model.Book
import com.d121211048.mybook.network.MybookApiService

/**
 * Default Implementation of repository that retrieves volumes data from underlying data source.
 */
class DefaultMybookRepository (
    private val mybookApiService: MybookApiService
) : MybookRepository {
    /** Retrieves list of Volumes from underlying data source */
    // Notes: List<Book>? NULLABLE
    override suspend fun getBooks(query: String): List<Book>? {
        return try {
            val res = mybookApiService.getBooks(query)
            if (res.isSuccessful) {
                res.body()?.items ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBook(id: String): Book? {
        return try {
            val res = mybookApiService.getBook(id)
            if (res.isSuccessful) {
                res.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}