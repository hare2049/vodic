package com.example.vodic.database.state

import com.example.vodic.database.entity.Smjer

data class SmjerState(
    val smjerovi: List<Smjer> = emptyList(),
    val naziv: String = "",
    val fakultetId: Int = 0,
    val odsjek: String = "",
)
