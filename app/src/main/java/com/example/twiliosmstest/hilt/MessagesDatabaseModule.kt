package com.example.twiliosmstest.hilt

import android.content.Context
import com.example.twiliosmstest.database.messages.MessagesDao
import com.example.twiliosmstest.database.messages.MessagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessagesDatabaseModule {

    @Provides
    @Singleton
    fun provideMessagesDatabase(
        @ApplicationContext context: Context
    ): MessagesDatabase {
        return MessagesDatabase.getMessagesDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMessagesDao(messagesDatabase: MessagesDatabase): MessagesDao {
        return messagesDatabase.messageDao()
    }
}
