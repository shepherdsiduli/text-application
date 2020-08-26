package com.shepherd.core.usecase

import com.shepherd.core.repository.TextRepository

class GetAllTexts (private val textRepository: TextRepository) {
    suspend operator fun invoke() = textRepository.getAllTexts()
}