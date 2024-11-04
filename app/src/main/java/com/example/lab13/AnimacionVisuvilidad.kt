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
                        onNavigateToAnimacionv4 = { navigateToAnimacionv4() } // Nueva navegación
                    )
                }
            }
        }
    }

    // Método para navegar a Animacionv2
    private fun navigateToAnimacionv2() {
        val intent = Intent(this, Animacionv2::class.java)
        startActivity(intent)
    }

    // Método para navegar a Animacionv3
    private fun navigateToAnimacionv3() {
        val intent = Intent(this, Animacionv3::class.java)
        startActivity(intent)
    }

    // Método para navegar a Animacionv4
    private fun navigateToAnimacionv4() {
        val intent = Intent(this, Animacionv4::class.java)
        startActivity(intent)
    }
}

@Composable
fun AnimatedVisibilityExample(
    onNavigateToAnimacionv2: () -> Unit,
    onNavigateToAnimacionv3: () -> Unit,
    onNavigateToAnimacionv4: () -> Unit // Parámetro para la nueva navegación
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

        // Botón para ir a Animacionv2
        Button(onClick = onNavigateToAnimacionv2) {
            Text(text = "Ir a Animacionv2")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ir a Animacionv3
        Button(onClick = onNavigateToAnimacionv3) {
            Text(text = "Ir a Animacionv3")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nuevo botón para ir a Animacionv4
        Button(onClick = onNavigateToAnimacionv4) {
            Text(text = "Ir a Animacionv4")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedVisibilityPreview() {
    Lab13Theme {
        AnimatedVisibilityExample(onNavigateToAnimacionv2 = {}, onNavigateToAnimacionv3 = {}, onNavigateToAnimacionv4 = {})
    }
}
