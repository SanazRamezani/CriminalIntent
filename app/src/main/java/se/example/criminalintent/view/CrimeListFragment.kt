package se.example.criminalintent.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import se.example.criminalintent.CrimeAdapter
import se.example.criminalintent.CrimeAdapterCallbacks
import se.example.criminalintent.R
import se.example.criminalintent.model.Crime
import se.example.criminalintent.viewmodel.CrimeListViewModel
import java.util.*

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment(), CrimeAdapterCallbacks {

    private var callbacks: Callbacks? = null
    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = null

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        crimeListViewModel.crimeListLiveData.observe(viewLifecycleOwner, Observer { crimes ->
            crimes?.let {
                Log.i(TAG, "Got crimes ${crimes.size}")
                updateUI(crimes)
            }
        })
    }

    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeAdapter(crimes, this)
        crimeRecyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onItemClicked(crimeId: UUID) {
        callbacks?.onCrimeSelected(crimeId)
    }

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }
}