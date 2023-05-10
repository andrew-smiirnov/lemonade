package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    // currentStep позволяет сохранять состояние приложения при повторных компоновках
    var currentStep by remember { mutableStateOf(1) }
    // Основная компоновка приложения
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(android.graphics.Color.YELLOW),
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (currentStep) {
                1 -> {
                    TextAndImage(
                        R.drawable.lemon_tree,
                        R.string.lemon_tree_content_description,
                        R.string.lemon_tree,
                        onImageClick = { currentStep = 2 }
                    )
                }

                2 -> {
                    TextAndImage(
                        R.drawable.lemon_squeeze,
                        R.string.lemon_squeeze_content_description,
                        R.string.lemon_squeeze,
                        onImageClick = { currentStep = 3 }
                    )
                }

                3 -> {
                    TextAndImage(
                        R.drawable.lemon_drink,
                        R.string.lemon_drink_content_description,
                        R.string.lemon_drink,
                        onImageClick = { currentStep = 4 }
                    )
                }

                4 -> {
                    TextAndImage(
                        R.drawable.lemon_restart,
                        R.string.lemon_restart_content_description,
                        R.string.lemon_empty_glass,
                        onImageClick = { currentStep = 1 }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonApp()
    }
}

@Composable
fun TextAndImage(
    paint: Int,
    content: Int,
    text: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(2.dp, color = Color(0xFF69CDD8))
            )
            {
                Image(
                    painter = painterResource(paint),
                    contentDescription = stringResource(content),
                    modifier = Modifier
                        .wrapContentSize()
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(text))
        }
    }
}
