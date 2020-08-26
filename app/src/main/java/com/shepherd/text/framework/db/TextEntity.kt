package com.shepherd.text.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shepherd.core.data.Text


@Entity(tableName = "text")
data class TextEntity (
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
){
    companion object{
        fun fromText(text: Text) = TextEntity(text.content, text.creationTime, text.updateTime, text.id)
    }

    fun toText() = Text(content, creationTime, updateTime, id)
}