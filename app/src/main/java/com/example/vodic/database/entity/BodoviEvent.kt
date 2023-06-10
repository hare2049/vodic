package com.example.vodic.database.entity

sealed interface BodoviEvent {
    object SaveBodovi: BodoviEvent
    data class setBrojPitanja(val brojPitanja: Int): BodoviEvent
    data class setOdgovor(val odgovor: Int): BodoviEvent
    data class setSmjerId(val smjerId: Int): BodoviEvent
    data class setBodovi(val bodovi: String): BodoviEvent
}