package se.example.criminalintent.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.example.criminalintent.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(currentFragment == null) {
            val fragment =
                CrimeListFragment.newInstance()//CrimeFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}
