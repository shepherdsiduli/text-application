package com.shepherd.core.repository

import com.shepherd.core.data.Text

interface TextDataSource {
    suspend fun add(text: Text)

    suspend fun get(id: Long): Text?

    suspend fun getAll(): List<Text>

    suspend fun remove(text: Text)
}