package ru.dubr.fragmentnavigationexample.contracts

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import ru.dubr.fragmentnavigationexample.Planet

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showPlanetDetailScreen(planet: Planet)

    fun showPlanetSelectionScreen(planet: Planet)

    fun goBack()

    fun <T: Parcelable> publishResult(result: T)

    fun <T: Parcelable> listenResult(clazz: Class<T>, owner: LifecycleOwner, listener: ResultListener<T>)
}


