package com.echonote.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.echonote.presentation.viewmodel.MainNotesViewModel
import java.time.LocalDateTime

@ExperimentalMaterial3Api
@Composable
fun NotesScreen(mainNotesViewModel: MainNotesViewModel) {
    val uiState by mainNotesViewModel.notesOverViewState.collectAsStateWithLifecycle()
    val notesMetaDataWithImages =
        remember { uiState.noteMetaData.filter { it.images.isNotEmpty() } }
    val notesMetaDataWithoutImages =
        remember { uiState.noteMetaData.filter { it.images.isEmpty() } }
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Notes with images", modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notesMetaDataWithImages) { item ->
                NoteCard(
                    title = item.title,
                    dateTime = item.created_at!!,
                    pictures = item.images,
                    modifier = Modifier,
                    id = item.id
                )
            }
        }

        Text("Notes", modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notesMetaDataWithoutImages) { item ->
                NoteCard(
                    title = item.title,
                    dateTime = item.created_at!!,
                    pictures = null,
                    modifier = Modifier,
                    id = item.id
                )
            }
        }

    }

}


@ExperimentalMaterial3Api
@Composable
fun NoteCard(
    title: String,
    dateTime: LocalDateTime,
    pictures: List<String>?,
    modifier: Modifier = Modifier,
    id: Int,
) {
    Card(modifier = modifier, onClick = {}) {

        Text(title, modifier = Modifier.semantics { title })
        Text(
            dateTime.toString(),
            modifier = Modifier.semantics {
                contentDescription = "Time this was last updated, ${dateTime}"
            })
        if (pictures != null) {
            CarouselNotes(noteImages = pictures)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun CarouselNotes(noteImages: List<String>) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { noteImages.count() }, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp), preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = noteImages[i]
        AsyncImage(
            model = item, contentDescription = null, modifier = Modifier
                .height(205.dp)
                .maskClip(MaterialTheme.shapes.extraLarge),
            contentScale = ContentScale.Crop
        )
    }
}