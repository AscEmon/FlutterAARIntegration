package com.example.testaar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testaar.ui.theme.TestAARIntegrationTheme
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.embedding.engine.FlutterEngine
import android.util.Log
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : ComponentActivity() {

    private val CHANNEL = "com.example.testaar/native"
    private var flutterEngine: FlutterEngine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Flutter engine
        flutterEngine = FlutterEngine(this).apply {
            // Start Flutter engine and set up method channel
            dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )
        }

        // Setup the content and UI
        setContent {
            MainContent(onFlutterClick = { openFlutterModule() })
        }
    }

    private fun openFlutterModule() {
        // Start FlutterActivity after initializing FlutterEngine
        val intent = FlutterActivity.createDefaultIntent(this)
        startActivity(intent)

        // Ensure method channel is set up before sending data
        flutterEngine?.let { engine ->
            val channel = MethodChannel(engine.dartExecutor, CHANNEL)

            channel.invokeMethod("sendData", "Hello from Native Android!") // Send data to Flutter
            Log.d("MainActivity", "Sending data to Flutter: Hello from Native Android!")

        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, onFlutterClick: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { onFlutterClick() }) {
            Text(text = "Open Flutter Module")
        }
    }
}