

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAARIntegrationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding),
                        onFlutterClick = { openFlutterModule() }
                    )
                }
            }
        }
    }

    // Function to open the Flutter module
    private fun openFlutterModule() {
        startActivity(
            FlutterActivity.createDefaultIntent(this)
        )
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

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    TestAARIntegrationTheme {
        MainContent(onFlutterClick = {})
    }
}
