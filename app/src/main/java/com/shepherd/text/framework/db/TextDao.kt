package com.shepherd.text.framework.db

import androidx.room.*

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTextEntity(textEntity:  TextEntity)

    @Query("SELECT * FROM text WHERE id = :id")
    suspend fun getTextEntity(id: Long): TextEntity?

    @Query("SELECT * FROM text")
    suspend fun getAllTextEntities(): List<TextEntity>

    @Delete
    suspend fun deleteTextEntity(textEntity: TextEntity)
}