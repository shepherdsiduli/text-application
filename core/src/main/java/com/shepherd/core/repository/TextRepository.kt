package com.shepherd.core.repository

import com.shepherd.core.data.Text

class TextRepository(private val dataSource: TextDataSource) {
    suspend fun addText(text: Text) = dataSource.add(text)

    suspend fun getText(id: Long) = dataSource.get(id)

    suspend fun getAllTexts() = dataSource.getAll()

    suspend fun removeText(text: Text) = dataSource.remove(text)
}