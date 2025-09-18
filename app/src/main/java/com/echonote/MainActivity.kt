package com.echonote

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echonote.presentation.screen.HomeScreen
import com.echonote.presentation.screen.VoiceRecordingScreen
import com.echonote.presentation.viewmodel.VoiceRecordingViewModel
import com.echonote.ui.theme.EchoNoteTheme
import kotlinx.serialization.Serializable

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class MainActivity : ComponentActivity() {
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private var permissionToRecordAccepted = false
    @Serializable
    object  HomeScreen
    @Serializable
    object  VoiceRecordScreen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      val voiceRecordingViewModel by viewModels<VoiceRecordingViewModel>()
        enableEdgeToEdge()
        setContent {
            EchoNoteTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = MainActivity.HomeScreen){
                        composable<MainActivity.HomeScreen> { HomeScreen(innerPadding)  }
                        composable<MainActivity.VoiceRecordScreen>{ VoiceRecordingScreen(voiceRecordingViewModel) }
                    }
                }
            }
        }
    }
}

 fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
) {
    onRequestPermissionsResult(requestCode, permissions, grantResults)
    val permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
        grantResults[0] == PackageManager.PERMISSION_GRANTED
    } else {
        false
    }
    if (!permissionToRecordAccepted) return
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EchoNoteTheme {

    }
}