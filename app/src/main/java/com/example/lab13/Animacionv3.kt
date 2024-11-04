package com.example.lab13

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

class Animacionv3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SizeAndPositionAnimation(
                        onNavigateToAnimacionv2 = { navigateToAnimacionv2() },
                        onNavigateToAnimacionVisuvilidad = { navigateToAnimacionVisuvilidad() }
                    )
                }
            }
        }
    }

    private fun navigateToAnimacionv2() {
        val intent = Intent(this, Animacionv2::class.java)
        startActivity(intent)
    }

    private fun navigateToAnimacionVisuvilidad() {
        val intent = Intent(this, AnimacionVisuvilidad::class.java)
        startActivity(intent)
    }
}

@Composable
fun SizeAndPositionAnimation(onNavigateToAnimacionv2: () -> Unit, onNavigateToAnimacionVisuvilidad: () -> Unit) {
    var isSmall by remember { mutableStateOf(true) }
    val size: Dp by animateDpAsState(targetValue = if (isSmall) 100.dp else 200.dp)
    val offsetX: Dp by animateDpAsState(targetValue = if (isSmall) 0.dp else 150.dp)
    val offsetY: Dp by animateDpAsState(targetValue = if (isSmall) 0.dp else 150.dp)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isSmall = !isSmall }) {
            Text(text = "Mover y Cambiar Tamaño")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offsetX, y = offsetY)
                .background(Color.Blue)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ir a Animacionv2
        Button(onClick = onNavigateToAnimacionv2) {
            Text(text = "Ir a Animacionv2")
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
fun SizeAndPositionPreview() {
    Lab13Theme {
        SizeAndPositionAnimation(onNavigateToAnimacionv2 = {}, onNavigateToAnimacionVisuvilidad = {})
    }
}
