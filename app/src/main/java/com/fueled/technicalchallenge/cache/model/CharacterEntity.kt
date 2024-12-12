package com.fueled.technicalchallenge.cache.model

import androidx.paging.PagingSource
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "resource_uri") val resourceURI: String,
    @ColumnInfo(name = "full_image_url") val fullImageUrl: String,
    @ColumnInfo(name = "defaultImageUrl") val defaultImageUrl: String,
) {

    @androidx.room.Dao
    interface Dao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(characters: List<CharacterEntity>)

        @Query("SELECT * FROM characterentity")
        fun pagingSource(): PagingSource<Int, CharacterEntity>

        @Query("SELECT * FROM characterentity WHERE id LIKE :characterId")
        suspend fun getById(characterId: Int): CharacterEntity?

        @Query("DELETE FROM characterentity")
        suspend fun clearAll()
    }
}
