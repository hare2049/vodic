package com.example.vodic.database.entity

sealed interface FakultetEvent {
    object SaveFakultet: FakultetEvent
    data class setNaziv(val naziv: String): FakultetEvent
}