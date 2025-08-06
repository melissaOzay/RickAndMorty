package com.developer.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.developer.rickandmorty.features.data.local.CharacterAppDB
import com.developer.rickandmorty.features.data.local.dao.CharacterDao
import com.developer.rickandmorty.features.data.local.db.CharacterLocalDS
import com.developer.rickandmorty.features.data.local.db.CharacterLocalDSImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDBModule {

    @Provides
    @Singleton
    fun provideCharacterAppDB(@ApplicationContext context: Context): CharacterAppDB {
        return Room.databaseBuilder(
            context,
            CharacterAppDB::class.java,
            "character_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(characterAppDB: CharacterAppDB): CharacterDao {
        return characterAppDB.getCharacterDao()
    }
    
    @Provides
    @Singleton
    fun provideCharacterLocalDS(characterDao: CharacterDao): CharacterLocalDS {
        return CharacterLocalDSImpl(characterDao)
    }
}