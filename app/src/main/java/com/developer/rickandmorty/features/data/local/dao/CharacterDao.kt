package com.developer.rickandmorty.features.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.developer.rickandmorty.features.data.local.entity.CharacterEntity
import com.developer.rickandmorty.features.data.local.entity.EpisodeEntity

@Dao
interface CharacterDao {
    @Insert
    suspend fun addFavorite(characterEntity: CharacterEntity)

    @Insert
    suspend fun addEpisode(episodeEntity: EpisodeEntity)

    @Query("SELECT * FROM episode")
    suspend fun getEpisodeList(): List<EpisodeEntity>

    @Query("SELECT * FROM character")
    suspend fun getCharacterList(): List<CharacterEntity>

    @Query("SELECT EXISTS(SELECT * FROM character WHERE characterId = :characterId)")
    suspend fun isImageFavorite(characterId : Int) : Boolean
    
    @Query("DELETE FROM character WHERE characterId = :characterId")
    suspend fun removeFavorite(characterId: Int)
}