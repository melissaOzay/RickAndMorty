package com.developer.rickandmorty.features.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developer.rickandmorty.features.data.local.dao.CharacterDao
import com.developer.rickandmorty.features.data.local.entity.CharacterEntity
import com.developer.rickandmorty.features.data.local.entity.EpisodeEntity

@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class CharacterAppDB : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}