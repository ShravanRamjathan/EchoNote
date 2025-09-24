package com.echonote.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.echonote.presentation.viewmodel.NotesViewModel
import java.time.LocalDateTime

@Composable
fun NoteScreen(notesViewModel: NotesViewModel) {
    val addNoteState by notesViewModel.noteState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally ) {
        Text("Notes", Modifier.semantics { heading() })
        InputText(addNoteState.title, "This is for the title of the note")
        NoteInput(addNoteState.content, "This is where we the user inputs a note")
    }
}

@Composable
fun NoteCard(
    title: String,
    dateTime: LocalDateTime,
    hasAIUse: Boolean,
    pictures: List<ImageBitmap>,
    modifier: Modifier = Modifier,
    id: Int,
) {
    var noteThumbNail: ImageBitmap? = null
    if (pictures.isNotEmpty()) noteThumbNail = pictures[0]
    if (pictures.size > 1) {
        // we will create a box in the center
    }
    Card(modifier = modifier, onClick = {}) {

        Text(title, modifier = Modifier.semantics { title })
        Text(
            dateTime.toString(),
            modifier = Modifier.semantics {
                contentDescription = "Time this was last updated, ${dateTime}"
            })
        if (noteThumbNail != null) {
            Row {
                Image(
                    bitmap = noteThumbNail,
                    contentDescription = "This is the the image of the note",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun InputText(textState: TextFieldState, description: String) { // adding semantics for my reference
    OutlinedTextField(
        modifier = Modifier
            .padding(20.dp)
            .semantics { contentDescription = description },
        state = textState,

    )
}

@Composable
fun NoteInput(noteState: TextFieldState, description: String) {
    val scrollState = rememberScrollState()
 OutlinedTextField(  modifier = Modifier
     .height(300.dp)
     .fillMaxWidth(0.9f)
     .padding(20.dp)
     .semantics { contentDescription = description },
     state = noteState,
     scrollState = scrollState)
}