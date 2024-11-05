package com.example.lab13

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

class Animacionv5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CombinedAnimationExample()
                }
            }
        }
    }

    @Composable
    fun CombinedAnimationExample() {
        var isLarge by remember { mutableStateOf(false) }
        var isVisible by remember { mutableStateOf(true) }
        var isDarkMode by remember { mutableStateOf(false) }

        // Animación de tamaño y color
        val size: Dp by animateDpAsState(targetValue = if (isLarge) 200.dp else 100.dp)
        val color by animateColorAsState(targetValue = if (isLarge) Color.Red else Color.Blue)

        // Animación para el botón
        val buttonOffset by animateDpAsState(targetValue = if (isVisible) 0.dp else 150.dp)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Elemento que cambia de tamaño y color
            Box(
                modifier = Modifier
                    .size(size)
                    .background(color)
                    .clickable { isLarge = !isLarge }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón que se desplaza y desaparece
            AnimatedVisibility(visible = isVisible) {
                Button(
                    modifier = Modifier.offset(x = buttonOffset),
                    onClick = { isVisible = false }
                ) {
                    Text(text = "Desaparecer")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para alternar entre modo claro y oscuro
            Button(onClick = { isDarkMode = !isDarkMode }) {
                Text(text = if (isDarkMode) "Modo Claro" else "Modo Oscuro")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nuevo botón para navegar a AnimacionVisuvilidad
            Button(onClick = { navigateToAnimacionVisuvilidad() }) {
                Text(text = "Ir a AnimacionVisuvilidad")
            }
        }
    }

    // Método para navegar a AnimacionVisuvilidad
    private fun navigateToAnimacionVisuvilidad() {
        val intent = Intent(this, AnimacionVisuvilidad::class.java)
        startActivity(intent)
    }

    @Preview(showBackground = true)
    @Composable
    fun CombinedAnimationPreview() {
        Lab13Theme {
            CombinedAnimationExample()
        }
    }
}
