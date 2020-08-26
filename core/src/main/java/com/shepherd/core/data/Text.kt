package com.shepherd.core.data

data class Text(
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0,
)