package com.developer.rickandmorty.features.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.developer.rickandmorty.features.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Insert
    suspend fun addFavorite(characterEntity: CharacterEntity)

    @Query("SELECT * FROM character")
    suspend fun getCharacterList(): List<CharacterEntity>

    @Query("SELECT EXISTS(SELECT * FROM character WHERE characterId = :characterId)")
    suspend fun isImageFavorite(characterId : Int) : Boolean
    
    @Query("DELETE FROM character WHERE characterId = :characterId")
    suspend fun removeFavorite(characterId: Int)
}