package com.tneagu.appnavigation

import kotlinx.coroutines.flow.StateFlow

interface AppNavigator {
    val navActions: StateFlow<NavigationAction?>
    fun navigate(navAction: NavigationAction)
}