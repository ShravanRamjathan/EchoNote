package com.echonote

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echonote.presentation.screen.HomeScreen
import com.echonote.presentation.screen.NoteScreen
import com.echonote.presentation.screen.VoiceRecordingScreen
import com.echonote.presentation.viewmodel.MainNotesViewModel
import com.echonote.presentation.viewmodel.VoiceRecordingViewModel
import com.echonote.ui.theme.EchoNoteTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

@Serializable
object HomeScreenRoute

@Serializable
object VoiceRecordScreenRoute

@Serializable
object NotesScreenRoute
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
        val voiceRecordingViewModel by viewModels<VoiceRecordingViewModel>()
        val mainNotesViewModel by viewModels<MainNotesViewModel>()
        enableEdgeToEdge()

        setContent {
            EchoNoteTheme {
                val navController = rememberNavController()
                val brush = Brush.linearGradient(listOf(Color.Black,Color.DarkGray, Color.Green))
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                    , color = Color.Transparent
                ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()

                ) { innerPadding ->

                        NavHost(navController = navController, startDestination = HomeScreenRoute, modifier = Modifier.padding(innerPadding).background(brush)) {
                            composable<HomeScreenRoute> {
                                HomeScreen(onNavigateToVoice = {
                                    navController.navigate(
                                        VoiceRecordScreenRoute
                                    )
                                }, onNavigateToNotes = {navController.navigate(NotesScreenRoute)})
                            }
                            composable<VoiceRecordScreenRoute> {
                                VoiceRecordingScreen(
                                    voiceRecordingViewModel,

                                    )
                            }
                            composable<NotesScreenRoute>{ NoteScreen(mainNotesViewModel) }
                        }
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) return
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EchoNoteTheme {

    }
}