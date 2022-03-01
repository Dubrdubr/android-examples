package ru.dubr.fragmentnavigationexample.fragments.contracts

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitleRes(): Int
}