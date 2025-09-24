package com.echonote.presentation.state

import android.graphics.Bitmap
import androidx.compose.foundation.text.input.TextFieldState

data class NoteState(
    val title: TextFieldState = TextFieldState(),
    val content: TextFieldState = TextFieldState(),
    val images: List<Bitmap>  = emptyList<Bitmap>(),
    val isAI:Boolean = false,


    val titleError:String = "",
    val contentError:String= "",
    val error:String = "",
    val isLoading:Boolean = false,
    val isSuccess:Boolean  = false
)