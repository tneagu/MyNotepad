package com.tneagu.appnavigation

internal object BackNavigation {
    fun back() = object : NavigationAction {
        override val destination: String = BACK_NAVIGATION
    }
}