package com.example.vodic.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.vodic.R

@Composable
fun StartScreen(
    onNextButtonClicked: () -> Unit,
){



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#DFF1FF"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.weight(0.7f), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.unsa), contentDescription = "UNSA", modifier = Modifier.fillMaxSize(0.8f))
        }
        Row(modifier = Modifier.weight(0.1f), verticalAlignment = Alignment.CenterVertically) {

            Text(text = "PRONAĐI FAKULTET ZA SEBE!", fontWeight = FontWeight.Bold, fontSize = 20.sp, fontFamily = FontFamily.Serif)
        }
        Row(modifier = Modifier.weight(0.2f), verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { onNextButtonClicked() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(android.graphics.Color.parseColor("#28578b"))
                ),
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.7f)
            ) {
                Text(text = "Pokreni", color = Color.White, fontFamily = FontFamily.Serif)
            }
        }
    }
}