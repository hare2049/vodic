package com.example.vodic.database.state

import com.example.vodic.database.entity.Bodovi

data class BodoviState(
    val listaBodova: List<Bodovi> = emptyList(),
    val brojPitanja: Int = 0,
    val odgovor: Int = 0,
    val smjerId: Int = 0,
    val bodovi: Int = 0,
)
