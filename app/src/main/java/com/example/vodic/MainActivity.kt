package com.example.vodic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.vodic.database.VodicDatabase
import com.example.vodic.database.VodicViewModel
import com.example.vodic.database.dao.FakultetDao
import com.example.vodic.database.entity.Fakultet
import com.example.vodic.database.entity.FakultetEvent
import com.example.vodic.database.entity.SmjerEvent
import com.example.vodic.ui.theme.VodicTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            VodicDatabase::class.java,
            "vodic.db"
        ).build()
    }

    private val viewModel by viewModels<VodicViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel/*izbrisao ?*/> create(modelClass: Class<T>): T {
                return VodicViewModel(db.fakultetDao, db.smjerDao, db.bodoviDao, db.pitanjaDao, db.odgovoriDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VodicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    VodicScreen(
                        viewModel
                    )
                }
            }
        }
        viewModel.start()
        viewModel.insertData()
        viewModel.ubaci()
    }
}
