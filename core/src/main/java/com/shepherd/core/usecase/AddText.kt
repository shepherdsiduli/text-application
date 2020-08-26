package com.shepherd.core.usecase

import com.shepherd.core.data.Text
import com.shepherd.core.repository.TextRepository

class AddText (private val textRepository: TextRepository) {
    suspend operator fun invoke(text : Text) = textRepository.addText(text)
}