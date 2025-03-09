package com.example.twiliosmstest.hilt

import com.example.twiliosmstest.database.MessagesDao
import com.example.twiliosmstest.database.MessagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessagesRepositoryModule {

    @Provides
    @Singleton
    fun provideMessagesRepository(dao: MessagesDao): MessagesRepository {
        return MessagesRepository(dao)
    }
}