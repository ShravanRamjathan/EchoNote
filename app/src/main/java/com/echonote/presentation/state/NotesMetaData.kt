package com.echonote.presentation.state

import android.graphics.Bitmap
import java.time.LocalDateTime

data class NotesMetaData(
    val id:Int = 0,
    val title:String = "",
    val images:List<Bitmap> = emptyList<Bitmap>(),
    val lastModifiedDate: LocalDateTime = LocalDateTime.MIN
)
