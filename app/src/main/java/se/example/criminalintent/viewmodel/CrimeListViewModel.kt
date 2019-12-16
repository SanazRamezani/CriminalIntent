package se.example.criminalintent.viewmodel

import androidx.lifecycle.ViewModel
import se.example.criminalintent.database.CrimeRepository

class CrimeListViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()
}