package com.echonote.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.echonote.presentation.viewmodel.MainNotesViewModel

@Composable
fun NotesScreen(mainNotesViewModel: MainNotesViewModel) {
    val uiState by mainNotesViewModel.notesOverViewState.collectAsStateWithLifecycle()
    val notesMetaDataWithImages =
        remember { uiState.noteMetaData.filter { it.images.isNotEmpty() } }
    val notesMetaDataWithoutImages =
        remember { uiState.noteMetaData.filter { it.images.isEmpty() } }
    LazyColumn {
        items(notesMetaDataWithImages) { item ->

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
    ) { i->
        val item= noteImages[i]
    }
}