package com.echonote.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import kotlin.time.asClock

@Composable
fun NoteScreen() {
    Column {
        Text("Notes", Modifier.semantics { heading() })

    }
}
@Composable
fun NoteCard(title:String, dateTime: LocalDateTime, hasAIUse: Boolean, pictures: List<ImageBitmap>, modifier: Modifier = Modifier, id:Int){
   var noteThumbNail: ImageBitmap? =null
     if(pictures.isNotEmpty()) noteThumbNail = pictures[0]
    if(pictures.size>1){
        // we will create a box in the center
    }
    Card(modifier = modifier, onClick = {} ) {

        Text(title, modifier = Modifier.semantics { title })
        Text(
            dateTime.toString(),
            modifier = Modifier.semantics {
                contentDescription = "Time this was last updated, ${dateTime}"
            })
        if (noteThumbNail != null) {
            Row{ Image(bitmap =noteThumbNail, contentDescription = "This is the the image of the note", modifier = Modifier.size(20.dp)) }
        }
    }
}