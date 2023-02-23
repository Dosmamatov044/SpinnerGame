package com.example.spinnergame.di

import android.content.Context
import androidx.room.Room
import com.example.spinnergame.db.ResultDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToResultDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, ResultDatabase::class.java,
        "result.db"
    ).build()

    @Singleton
    @Provides
    fun provideToResultDao(
        db: ResultDatabase
    ) = db.toResultDao()

}