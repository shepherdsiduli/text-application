package com.shepherd.text.framework

import android.content.Context
import com.shepherd.core.data.Text
import com.shepherd.core.repository.TextDataSource
import com.shepherd.text.framework.db.DatabaseService
import com.shepherd.text.framework.db.TextEntity

class RoomTextDataSource(context: Context): TextDataSource {
    val textDao = DatabaseService.getInstance(context).textDao()

    override suspend fun add(text: Text) = textDao.addTextEntity(TextEntity.fromText(text))

    override suspend fun get(id: Long) = textDao.getTextEntity(id)?.toText()

    override suspend fun getAll() = textDao.getAllTextEntities().map { it.toText() }

    override suspend fun remove(text: Text) = textDao.deleteTextEntity(TextEntity.fromText(text))
}