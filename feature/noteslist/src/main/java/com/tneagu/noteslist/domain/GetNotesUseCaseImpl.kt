package com.tneagu.noteslist.domain

import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor() : GetNotesUseCase {
    override fun getNotes(): List<String> = listOf("Demo String")
}