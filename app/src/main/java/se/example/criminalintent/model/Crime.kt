package se.example.criminalintent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Crime(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    val date: Date = Date(),
    var isSolved: Boolean = false)