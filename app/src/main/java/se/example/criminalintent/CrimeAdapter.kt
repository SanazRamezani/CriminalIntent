package se.example.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.example.criminalintent.model.Crime
import java.util.*

class CrimeAdapter(private val crimes: List<Crime>, private val listener: CrimeAdapterCallbacks) : RecyclerView.Adapter<CrimeViewHolder>() {

    private lateinit var callbacks: CrimeAdapterCallbacks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
        callbacks = listener
        return CrimeViewHolder(view)
    }

    override fun getItemCount() = crimes.size

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.bind(crimes[position], callbacks)
    }
}

class CrimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private lateinit var crime: Crime
    private val titleTextView : TextView = itemView.findViewById(R.id.crime_title)
    private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
    private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

    fun bind(crime: Crime, callbacks: CrimeAdapterCallbacks?) {
        this.crime = crime
        titleTextView.text = this.crime.title
        dateTextView.text = this.crime.date.toString()
        solvedImageView.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

        itemView.setOnClickListener {
            callbacks?.let {
                it.onItemClicked(this.crime.id)
            }
        }
    }
}

interface CrimeAdapterCallbacks{
    fun onItemClicked(crimeId: UUID)
}