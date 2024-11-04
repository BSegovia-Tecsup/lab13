package com.example.lab13

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

class Animacionv4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedContentExample(
                        onNavigateToAnimacionVisuvilidad = { navigateToAnimacionVisuvilidad() }
                    )
                }
            }
        }
    }

    private fun navigateToAnimacionVisuvilidad() {
        val intent = Intent(this, AnimacionVisuvilidad::class.java)
        startActivity(intent)
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AnimatedContentExample(
        onNavigateToAnimacionVisuvilidad: () -> Unit
    ) {
        var currentState by remember { mutableStateOf("Cargando") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(
                targetState = currentState,
                transitionSpec = {
                    fadeIn(animationSpec = androidx.compose.animation.core.tween(300)) with
                            fadeOut(animationSpec = androidx.compose.animation.core.tween(300))
                }
            ) { state ->
                when (state) {
                    "Cargando" -> {
                        Text(text = "Cargando...", modifier = Modifier.padding(16.dp))
                    }
                    "Contenido" -> {
                        Text(text = "Contenido Cargado!", modifier = Modifier.padding(16.dp))
                    }
                    "Error" -> {
                        Text(text = "Ha Ocurrido un Error", modifier = Modifier.padding(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = { currentState = "Cargando" }) {
                    Text(text = "Cargar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { currentState = "Contenido" }) {
                    Text(text = "Mostrar Contenido")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { currentState = "Error" }) {
                    Text(text = "Mostrar Error")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para ir a AnimacionVisuvilidad
            Button(onClick = onNavigateToAnimacionVisuvilidad) {
                Text(text = "Ir a AnimacionVisuvilidad")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AnimatedContentPreview() {
        Lab13Theme {
            AnimatedContentExample(onNavigateToAnimacionVisuvilidad = {})
        }
    }
}
