/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.vodic.ui.navigation

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vodic.database.VodicViewModel
import com.example.vodic.database.entity.BodoviEvent
import com.example.vodic.database.entity.FakultetEvent
import com.example.vodic.database.entity.SmjerEvent
import com.example.vodic.database.state.BodoviState
import com.example.vodic.database.state.FakultetState
import com.example.vodic.database.state.SmjerState
import com.example.vodic.ui.EndScreen
import com.example.vodic.ui.QuestionScreen
import com.example.vodic.ui.StartScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun VodicAppBar(
    currentQuestion: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(currentQuestion, color = Color.White) },
        modifier = modifier,
        backgroundColor = Color(0xFF28578B),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }
    )
}



@Composable
fun VodicNavHost(
    viewModel: VodicViewModel,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {

    val state by viewModel.uiState.collectAsState()

    val questions = state.pitanja;

    Log.d("pitanja", state.pitanja.size.toString())

    val backStackEntry by navController.currentBackStackEntryAsState()

    //val questions = listOf<Int>(1,2,3,4,5,6)

    val currentQuestionId = backStackEntry?.arguments?.getInt("questionId") ?: 0
    //val currentQuestion = if (currentQuestionId > 0 && !(currentQuestionId == questions.size)) "Question $currentQuestionId" else "NoTopBar"

    val currentQuestion = remember { mutableStateOf(1) }
    val inQuestions = remember { mutableStateOf(false) }
    val putanja = remember { mutableStateOf(true) }


    Scaffold(
            topBar = {
                if(inQuestions.value){
                    VodicAppBar(
                        currentQuestion = "Pitanje ${currentQuestion.value}",
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = {
                            Log.d("u topappbar", "u topappbar")

                            viewModel.setPutanja(false)

                            currentQuestion.value -= 1

                            navController.navigateUp()

                        }
                    )
                }
                else{}
            }
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "StartScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("StartScreen"){
                inQuestions.value = false
                StartScreen(
                    onNextButtonClicked = {
                        currentQuestion.value = 1
                        navController.navigate("QuestionScreen")
                    }
                )
            }

            composable("QuestionScreen"){
                if(currentQuestion.value <= questions.size) {
                    inQuestions.value = true
                    QuestionScreen(
                        putanja = state.putanja,
                        currentQuestion = currentQuestion.value,
                        viewModel = viewModel,
                        onNextButtonClicked = {
                            currentQuestion.value += 1
                            viewModel.setPutanja(true)
                            navController.navigate("QuestionScreen")
                        },
                    )
                }
                else{
                    inQuestions.value = false
                    EndScreen(
                        viewModel = viewModel,
                        onNextButtonClicked = {
                            navController.navigate("StartScreen")
                        }
                    )
                }


            }

            /*
            composable(
                "QuestionScreen/{questionId}",
                arguments = listOf(navArgument("questionId") { type = NavType.IntType })
            ) { backStackEntry ->
                val questionId = backStackEntry.arguments?.getInt("questionId")
                if (questionId != null) {
                    if(questionId < questions.size){
                        questionId?.let { id ->
                            var id = id + 1;
                            QuestionScreen(
                                viewModel,
                                onNextButtonClicked = {
                                    navController.navigate("QuestionScreen/$id")
                                }
                            )
                        }
                    }
                    else{
                        EndScreen(
                            onNextButtonClicked = {
                                navController.navigate("StartScreen")
                            }
                        )
                    }
                }
            }

             */

        }
    }
}
