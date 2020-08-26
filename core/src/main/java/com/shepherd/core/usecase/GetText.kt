package com.shepherd.core.usecase

import com.shepherd.core.repository.TextRepository

class GetText (private val textRepository: TextRepository) {
    suspend operator fun invoke(id : Long) = textRepository.getText(id)
}