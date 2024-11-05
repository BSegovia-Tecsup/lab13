package com.example.lab13

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

class AnimacionVisuvilidad : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedVisibilityExample(
                        onNavigateToAnimacionv2 = { navigateToAnimacionv2() },
                        onNavigateToAnimacionv3 = { navigateToAnimacionv3() },
                        onNavigateToAnimacionv4 = { navigateToAnimacionv4() }, // Nueva navegación
                        onNavigateToAnimacionv5 = { navigateToAnimacionv5() }  // Nueva navegación a Animacionv5
                    )
                }
            }
        }
    }

    private fun navigateToAnimacionv2() {
        val intent = Intent(this, Animacionv2::class.java)
        startActivity(intent)
    }

    private fun navigateToAnimacionv3() {
        val intent = Intent(this, Animacionv3::class.java)
        startActivity(intent)
    }

    private fun navigateToAnimacionv4() {
        val intent = Intent(this, Animacionv4::class.java)
        startActivity(intent)
    }

    private fun navigateToAnimacionv5() {
        val intent = Intent(this, Animacionv5::class.java)
        startActivity(intent)
    }
}

@Composable
fun AnimatedVisibilityExample(
    onNavigateToAnimacionv2: () -> Unit,
    onNavigateToAnimacionv3: () -> Unit,
    onNavigateToAnimacionv4: () -> Unit,
    onNavigateToAnimacionv5: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = if (isVisible) "Ocultar" else "Mostrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAnimacionv2) {
            Text(text = "Ir a Animacionv2")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAnimacionv3) {
            Text(text = "Ir a Animacionv3")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAnimacionv4) {
            Text(text = "Ir a Animacionv4")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAnimacionv5) {
            Text(text = "Ir a Animacionv5")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedVisibilityPreview() {
    Lab13Theme {
        AnimatedVisibilityExample(
            onNavigateToAnimacionv2 = {},
            onNavigateToAnimacionv3 = {},
            onNavigateToAnimacionv4 = {},
            onNavigateToAnimacionv5 = {}
        )
    }
}
