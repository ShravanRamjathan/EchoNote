package com.echonote

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echonote.presentation.screen.HomeScreen
import com.echonote.presentation.screen.VoiceRecordingScreen
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private var permissionToRecordAccepted = false
    private var recorder: MediaRecorder? = null

    private var player: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
        val voiceRecordingViewModel by viewModels<VoiceRecordingViewModel>()
        enableEdgeToEdge()

        setContent {
            EchoNoteTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = HomeScreenRoute) {
                        composable<HomeScreenRoute> {
                            HomeScreen(innerPadding, onNavigateToVoice = {
                                navController.navigate(
                                    VoiceRecordScreenRoute
                                )
                            })
                        }
                        composable<VoiceRecordScreenRoute> {
                            VoiceRecordingScreen(
                                voiceRecordingViewModel,

                                )
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