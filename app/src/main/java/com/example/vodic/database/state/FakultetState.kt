package com.example.vodic.database.state

import com.example.vodic.database.entity.Odgovori
import com.example.vodic.database.entity.Pitanja

data class FakultetState(
    val trenutnoPitanje: String = String(),
    val trenutniOdgovori: List<Odgovori> = emptyList(),
    val listaOdgovora: MutableList<String> = mutableListOf("","","","","","","","","","","",""),
    val pitanja: List<Pitanja> = emptyList(),
    val putanja: Boolean = true
)