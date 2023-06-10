package com.example.vodic.database.entity

sealed interface SmjerEvent {
    object SaveSmjer: SmjerEvent
    data class setNaziv(val naziv: String): SmjerEvent
    data class setFakultetId(val fakultetId: Int): SmjerEvent
    data class setOdsjek(val odsjek: String): SmjerEvent
}