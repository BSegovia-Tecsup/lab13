package com.example.lab13

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Animacionv5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                CombinedAnimationExample()
            }
        }
    }

    @Composable
    fun CombinedAnimationExample() {
        var colorIndex by remember { mutableStateOf(0) }
        var size by remember { mutableStateOf(100.dp) }
        var isVisible by remember { mutableStateOf(true) }
        var isButtonVisible by remember { mutableStateOf(true) }
        var offsetX by remember { mutableStateOf(0.dp) }
        var isDarkMode by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()

        // Lista de colores
        val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color(0xFFFFA500)) // Naranja
        val animatedColor by animateColorAsState(targetValue = colors[colorIndex])
        val animatedSize by animateDpAsState(targetValue = size)

        // Cambia el color de fondo según el modo
        val backgroundColor = if (isDarkMode) Color.Black else Color.White

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(backgroundColor), // Cambia el fondo aquí
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Botón para cambiar color
            Button(onClick = { colorIndex = (colorIndex + 1) % colors.size }) {
                Text("Cambiar Color")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Fila con botones de incremento y decremento de tamaño
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { size = (size - 20.dp).coerceAtLeast(20.dp) }) {
                    Text("-")
                }
                Button(onClick = { size += 20.dp }) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Elemento que cambia de tamaño y color
            Box(
                modifier = Modifier
                    .size(animatedSize)
                    .background(animatedColor)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Animación de desplazamiento usando animationSpec
            AnimatedVisibility(visible = isButtonVisible) {
                Button(
                    modifier = Modifier.offset(x = offsetX),
                    onClick = {
                        coroutineScope.launch {
                            val totalSteps = 30
                            val delayPerStep = 27L
                            val tweenSpec = TweenSpec<Float>(durationMillis = (totalSteps * delayPerStep).toInt(), easing = FastOutSlowInEasing)

                            // Desplazamiento a la derecha
                            repeat(totalSteps) { step ->
                                offsetX = 200.dp * ((step + 1) / totalSteps.toFloat())
                                delay(delayPerStep)
                            }

                            // Vuelve al lado izquierdo
                            offsetX = (-200).dp
                            delay(delayPerStep * 5)

                            repeat(totalSteps) { step ->
                                offsetX = (-200).dp * ((totalSteps - step) / totalSteps.toFloat())
                                delay(delayPerStep)
                            }

                            offsetX = 0.dp
                        }
                    }
                ) {
                    Text("Desplazamiento")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón que desaparece y reaparece después de 3 segundos
            AnimatedVisibility(visible = isVisible) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isVisible = false
                            delay(3000)
                            isVisible = true
                        }
                    }
                ) {
                    Text("Desaparecer")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para alternar entre modo claro y oscuro
            Button(onClick = { isDarkMode = !isDarkMode }) {
                Text(if (isDarkMode) "Modo Claro" else "Modo Oscuro")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para redirigir a AnimacionVisuvilidad
            Button(onClick = {
                val intent = Intent(this@Animacionv5, AnimacionVisuvilidad::class.java)
                startActivity(intent)
            }) {
                Text("Ir a AnimacionVisuvilidad")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CombinedAnimationPreview() {
        Lab13Theme {
            CombinedAnimationExample()
        }
    }
}
