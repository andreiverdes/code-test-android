package com.fueled.technicalchallenge.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fueled.technicalchallenge.cache.model.CharacterEntity
import com.fueled.technicalchallenge.cache.model.RemoteKey

@Database(
    entities = [
        CharacterEntity::class,
        RemoteKey::class,
    ],
    version = 1
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterEntity.Dao
    abstract fun remoteKeyDao(): RemoteKey.Dao
}