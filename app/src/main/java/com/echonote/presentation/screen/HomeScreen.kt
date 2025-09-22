package com.echonote.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.echonote.ui.theme.EchoNoteTheme

@Composable
fun HomeScreen( onNavigateToVoice:()-> Unit) {
    val temp: String = "Shravan"

    Column(modifier = Modifier.fillMaxSize()) {
        Banner("Echo Note", temp)
        Spacer(Modifier.size(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Column(
                // change this to lazy row when coding properly
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                HomeButton("Add a note", onNavigate = {})
                HomeButton("Note Templates", onNavigate = {})
                HomeButton("Voice record", onNavigate = {onNavigateToVoice()})
            }

        }
    }

}

@Composable
@Preview
fun myPreview() {
    EchoNoteTheme {

    }
}

@Composable
fun Banner(heading: String, username: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                MaterialTheme.colorScheme.onSecondary
            )
    ) {
        Text(heading, Modifier.weight(0.8f), textAlign = TextAlign.Center)
        Text(username, Modifier.weight(0.2f), textAlign = TextAlign.Center)


    }
}

// refactor the icon
@Composable
fun HomeButton(content: String, modifier: Modifier = Modifier, onNavigate:()->Unit) {
    ElevatedButton(
        onClick = {onNavigate()},
        elevation = ButtonDefaults.buttonElevation(10.dp),
        modifier = Modifier.defaultMinSize(minWidth = 200.dp).height(80.dp),

    ) {
        Text(content, modifier = Modifier.fillMaxWidth(0.8f).padding(5.dp))
        Icon(Icons.Filled.AddCircle, content)
    }
}
