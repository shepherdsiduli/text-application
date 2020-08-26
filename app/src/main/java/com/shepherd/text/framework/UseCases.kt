package com.shepherd.text.framework

import com.shepherd.core.usecase.*

data class UseCases (
    val addText: AddText,
    val getAllTexts: GetAllTexts,
    val getText: GetText,
    val removeText: RemoveText
)