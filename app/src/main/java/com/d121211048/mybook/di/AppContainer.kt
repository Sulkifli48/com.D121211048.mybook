package com.d121211048.mybook.di

import com.d121211048.mybook.data.MybookRepository
import com.d121211048.mybook.network.MybookApiService

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val mybookApiService: MybookApiService
    val mybookRepository: MybookRepository
}