package ru.dubr.fragmentnavigationexample

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Planet(
    val name: String,
    val resId: Int
) : Parcelable {

    override fun toString(): String {
        return name
    }

    companion object {
        val DEFAULT = Planet("Mercury", R.drawable.mercury_1)
    }

}