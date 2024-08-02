package com.guizot.android_clean_architecture_boilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.guizot.android_clean_architecture_boilerplate.ui.theme.AndroidCleanArchitectureBoilerplateTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCleanArchitectureBoilerplateTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Small Top App Bar")
                            },
                        )
                    },

                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(all = Dp(value = 16.0F))
                            .border(
                                border = BorderStroke(
                                    width = Dp(value = 1.0F),
                                    color = Color.Black
                                ),
                                shape = RoundedCornerShape(24.0F),
                            )
                            .background(
                                color = Color.Gray.copy(alpha = 0.1F),
                                shape = RoundedCornerShape(24.0F),
                            )
                            .padding(all = Dp(value = 16.0F))
                    ) {
                        Text(
                            text = "Hellooooo!",
                        )
                    }
                }
            }
        }
    }
}