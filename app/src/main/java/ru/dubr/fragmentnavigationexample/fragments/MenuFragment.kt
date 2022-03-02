package ru.dubr.fragmentnavigationexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dubr.fragmentnavigationexample.Planet
import ru.dubr.fragmentnavigationexample.contracts.navigator
import ru.dubr.fragmentnavigationexample.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var planet: Planet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        planet = savedInstanceState?.getParcelable(KEY_PLANET) ?: Planet.DEFAULT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        navigator().listenResult(Planet::class.java, viewLifecycleOwner) {
            this.planet = it
        }

        binding.showDetailsButton.setOnClickListener { onShowDetailsPressed() }
        binding.selectPlanetButton.setOnClickListener { onSelectPlanetPressed() }
        binding.exitButton.setOnClickListener { onExitPressed() }

        return binding.root
    }

    private fun onShowDetailsPressed() {
        navigator().showPlanetDetailScreen(planet)
    }

    private fun onSelectPlanetPressed() {
        navigator().showPlanetSelectionScreen(planet)
    }

    private fun onExitPressed() {
        navigator().goBack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PLANET, planet)
    }

    companion object {
        private const val KEY_PLANET = "KEY_PLANET"
    }
}