package ru.dubr.fragmentnavigationexample.contracts

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitleRes(): Int
}