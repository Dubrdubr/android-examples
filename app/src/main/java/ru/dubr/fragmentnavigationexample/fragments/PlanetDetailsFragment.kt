package ru.dubr.fragmentnavigationexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.javafaker.Faker
import ru.dubr.fragmentnavigationexample.Planet
import ru.dubr.fragmentnavigationexample.R
import ru.dubr.fragmentnavigationexample.contracts.HasCustomTitle
import ru.dubr.fragmentnavigationexample.contracts.navigator
import ru.dubr.fragmentnavigationexample.databinding.FragmentDetailsBinding
import java.lang.IllegalArgumentException

class PlanetDetailsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentDetailsBinding

    private lateinit var planet: Planet

    private lateinit var fakerInfo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        planet = arguments?.getParcelable(ARGS_PLANET) ?: throw IllegalArgumentException("")
        fakerInfo = savedInstanceState?.getString(KEY_INFO) ?: createDescription()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setupPlanet()

        binding.okButton.setOnClickListener { navigator().goBack() }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_INFO, fakerInfo)
    }

    private fun setupPlanet() {
        binding.planetImageView.setImageResource(planet.resId)
        binding.planetNameTextView.text = planet.name
        binding.planetInfoTextView.text = fakerInfo
    }

    override fun getTitleRes() = R.string.details

    private fun createDescription() = Faker().lorem().sentences(15).toString()

    companion object {

        private const val ARGS_PLANET = "ARGS_PLANET"
        private const val KEY_INFO = "KEY_INFO"

        fun newInstance(planet: Planet): PlanetDetailsFragment {
            val args = Bundle()
            args.putParcelable(ARGS_PLANET, planet)
            val fragment = PlanetDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}