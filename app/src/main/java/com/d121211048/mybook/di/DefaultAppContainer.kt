package com.d121211048.mybook.di

import com.d121211048.mybook.data.MybookRepository
import com.d121211048.mybook.data.DefaultMybookRepository
import com.d121211048.mybook.network.MybookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DefaultAppContainer : AppContainer {
    //    private val json = Json {
//        ignoreUnknownKeys = true
//        explicitNulls = false
//    }
    override val mybookApiService: MybookApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(json
//                    .asConverterFactory("application/json".toMediaType()))
            .baseUrl(MybookApiService.BASE_URL)
            .build()
            .create()
    }

    override val mybookRepository: MybookRepository by lazy {
        DefaultMybookRepository(mybookApiService)
    }
}