package com.tneagu.appnavigation

import kotlinx.coroutines.flow.StateFlow

const val BACK_NAVIGATION = "back"
interface AppNavigator {
    val navActions: StateFlow<NavigationAction?>
    fun navigate(navAction: NavigationAction)
    fun back()
}