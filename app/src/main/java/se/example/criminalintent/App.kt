package se.example.criminalintent

import android.app.Application
import se.example.criminalintent.database.CrimeRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}