package com.example.vodic.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import com.example.vodic.R
import com.example.vodic.database.VodicViewModel

@Composable
fun EndScreen(
    viewModel: VodicViewModel,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#DFF1FF"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor("#DFF1FF"))),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.weight(0.8f), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.unsa), contentDescription = "UNSA", modifier = Modifier.fillMaxSize(0.8f))
            }
            Row(modifier = Modifier.weight(0.2f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = {
                        viewModel.start()
                        onNextButtonClicked()
                              },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(android.graphics.Color.parseColor("#28578b"))
                    ),
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(text = "Ponovi", color = Color.White, fontFamily = FontFamily.Serif)
                }
            }
        }
    }
}