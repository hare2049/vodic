package com.example.vodic

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vodic.database.VodicViewModel
import com.example.vodic.database.entity.BodoviEvent
import com.example.vodic.database.entity.FakultetEvent
import com.example.vodic.database.entity.SmjerEvent
import com.example.vodic.database.state.BodoviState
import com.example.vodic.database.state.FakultetState
import com.example.vodic.database.state.SmjerState
import com.example.vodic.ui.StartScreen
import com.example.vodic.ui.navigation.VodicNavHost

@Composable
fun VodicScreen(
    viewModel: VodicViewModel,
    ) {

    VodicNavHost(
        viewModel = viewModel,
    )
}