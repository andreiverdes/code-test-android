package com.fueled.technicalchallenge.cache.model

import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class RemoteKey(
    @PrimaryKey val characterId: Int,
    val nextKey: Int?
) {
    @androidx.room.Dao
    interface Dao {
        @Query("SELECT * FROM RemoteKey WHERE characterId = :id")
        suspend fun remoteKeysByCharacterId(id: Int): RemoteKey?

        @Query("DELETE FROM RemoteKey")
        suspend fun clearRemoteKeys()

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(remoteKeys: List<RemoteKey>)
    }
}