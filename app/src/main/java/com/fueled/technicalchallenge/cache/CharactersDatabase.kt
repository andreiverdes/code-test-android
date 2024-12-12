package com.fueled.technicalchallenge.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fueled.technicalchallenge.cache.model.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class,
    ],
    version = 1
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterEntity.Dao
}