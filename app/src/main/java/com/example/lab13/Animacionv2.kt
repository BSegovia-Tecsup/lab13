package com.example.lab13

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
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

class Animacionv2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ColorChangeExample { navigateToAnimacionVisuvilidad() }
                }
            }
        }
    }

    private fun navigateToAnimacionVisuvilidad() {
        val intent = Intent(this, AnimacionVisuvilidad::class.java)
        startActivity(intent)
    }
}

@Composable
fun ColorChangeExample(onNavigate: () -> Unit) {
    var isBlue by remember { mutableStateOf(true) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = spring() // Puedes cambiar a tween() o definir duración y otros parámetros
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isBlue = !isBlue }) {
            Text(text = "Cambiar Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(backgroundColor)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigate) {
            Text(text = "Ir a AnimacionVisuvilidad")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorChangePreview() {
    Lab13Theme {
        ColorChangeExample(onNavigate = {})
    }
}
