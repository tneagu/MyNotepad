package com.tneagu.notedetails.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tneagu.domain.entities.Note

@Composable
fun NoteDetails (note: Note){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = note.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = note.body,
            fontSize = 12.sp,
        )
    }
}