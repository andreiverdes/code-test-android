package com.fueled.technicalchallenge.di

import com.fueled.technicalchallenge.data.ApiConstants
import com.fueled.technicalchallenge.data.CharactersApi
import com.fueled.technicalchallenge.data.UnWrapperFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideCharacterApi(moshiConverterFactory: MoshiConverterFactory): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(UnWrapperFactory)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(CharactersApi::class.java)
    }
}