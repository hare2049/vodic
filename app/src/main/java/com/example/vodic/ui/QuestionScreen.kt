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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vodic.R
import com.example.vodic.database.VodicViewModel

@Composable
fun QuestionScreen(
    putanja: Boolean,
    currentQuestion: Int,
    viewModel: VodicViewModel,
    onNextButtonClicked: () -> Unit,
){
    val state by viewModel.uiState.collectAsState()

    val questions = state.pitanja;



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#DFF1FF"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.weight(0.7f), verticalAlignment = Alignment.CenterVertically) {
            Text(text = state.trenutnoPitanje, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center, fontFamily = FontFamily.Serif)
        }
        LazyColumn{
            items(state.trenutniOdgovori) {odgovor ->
                Row(modifier = Modifier.weight(0.2f), verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = {
                            if(currentQuestion < questions.size){
                                Log.d("u ifelsu", "u ifelsu")
                                if(putanja) {
                                    Log.d("u update1", "u update1")
                                    viewModel.update(odgovor.odgovor, state.trenutnoPitanje)
                                }
                                else{
                                    Log.d("u update2", "u update2")
                                    viewModel.update2(odgovor.odgovor, state.trenutnoPitanje)
                                }

                            }
                            onNextButtonClicked() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(android.graphics.Color.parseColor("#28578b"))
                        ),
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth(0.7f)
                    ) {
                        Text(text = odgovor.odgovor, color = Color.White, fontFamily = FontFamily.Serif)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))

    }
}