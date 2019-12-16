package se.example.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import se.example.criminalintent.model.Crime

@Database(entities = [Crime::class], version = 1, exportSchema = true)
@TypeConverters(CrimeTypeConverter::class)
abstract class CrimeDatabase : RoomDatabase() {

    abstract fun crimeDao(): CrimeDao

}