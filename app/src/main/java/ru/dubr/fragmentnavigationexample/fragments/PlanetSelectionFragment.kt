package ru.dubr.fragmentnavigationexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import ru.dubr.fragmentnavigationexample.Planet
import ru.dubr.fragmentnavigationexample.R
import ru.dubr.fragmentnavigationexample.contracts.CustomAction
import ru.dubr.fragmentnavigationexample.contracts.HasCustomAction
import ru.dubr.fragmentnavigationexample.contracts.HasCustomTitle
import ru.dubr.fragmentnavigationexample.contracts.navigator
import ru.dubr.fragmentnavigationexample.databinding.FragmentSelectionBinding
import java.lang.IllegalArgumentException

class PlanetSelectionFragment : Fragment(), HasCustomTitle, HasCustomAction {

    private lateinit var binding: FragmentSelectionBinding

    private lateinit var planet: Planet

    private val data = mutableListOf(
        Planet("Mercury", R.drawable.mercury_1),
        Planet("Venus", R.drawable.venus_2),
        Planet("Earth", R.drawable.earth_3),
        Planet("Mars", R.drawable.mars_4),
        Planet("Jupiter", R.drawable.jupiter_5),
        Planet("Saturn", R.drawable.saturn_6),
        Planet("Uranus", R.drawable.uranus_7),
        Planet("Neptune", R.drawable.neptun_8),
        Planet("Pluto", R.drawable.pluto_9)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        planet =
            savedInstanceState?.getParcelable(KEY_PLANET) ?: arguments?.getParcelable(ARGS_PLANET)
                    ?: throw IllegalArgumentException("need ARGS")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectionBinding.inflate(inflater, container, false)

        setupAdapter()

        val currentPlanet = data.indexOfFirst { it.name == planet.name }
        binding.planetSpinner.setSelection(currentPlanet)
        binding.confirmButton.setOnClickListener { onConfirmPressed() }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PLANET, planet)
    }

    override fun getCustomAction(): CustomAction {
        return CustomAction(
            R.drawable.ic_baseline_done_24,
            R.string.done,
            onCustomAction = { onConfirmPressed() }
        )
    }

    override fun getTitleRes() = R.string.selection

    private fun setupAdapter() {
        binding.planetSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            data
        )

        binding.planetSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    planet = data[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun onConfirmPressed() {
        navigator().publishResult(planet)
        navigator().goBack()
    }

    companion object {
        private const val KEY_PLANET = "KEY_PLANET"
        private const val ARGS_PLANET = "ARGS_PLANET"

        fun newInstance(planet: Planet): PlanetSelectionFragment {
            val args = Bundle()
            args.putParcelable(ARGS_PLANET, planet)
            val fragment = PlanetSelectionFragment()
            fragment.arguments = args
            return fragment
        }
    }
}