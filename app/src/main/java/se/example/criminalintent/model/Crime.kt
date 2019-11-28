package se.example.criminalintent.model

import java.util.*

data class Crime(
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    val date: Date = Date(),
    var isSolved: Boolean = false)