package ru.dubr.fragmentnavigationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import ru.dubr.fragmentnavigationexample.R
import ru.dubr.fragmentnavigationexample.contracts.Navigator
import ru.dubr.fragmentnavigationexample.contracts.ResultListener
import ru.dubr.fragmentnavigationexample.databinding.ActivityMainBinding
import ru.dubr.fragmentnavigationexample.fragments.MenuFragment
import ru.dubr.fragmentnavigationexample.fragments.PlanetDetailsFragment
import ru.dubr.fragmentnavigationexample.fragments.PlanetSelectionFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, MenuFragment())
                .commit()
        }


    }

    override fun showPlanetDetailScreen(planet: Planet) {
        launchFragment(PlanetDetailsFragment.newInstance(planet))
    }

    override fun showPlanetSelectionScreen(planet: Planet) {
        launchFragment(PlanetSelectionFragment.newInstance(planet))
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun <T : Parcelable> publishResult(result: T) {
        TODO("Not yet implemented")
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        TODO("Not yet implemented")
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}