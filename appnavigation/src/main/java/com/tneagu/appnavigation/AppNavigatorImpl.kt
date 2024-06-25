package com.tneagu.appnavigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppNavigatorImpl : AppNavigator {
    private val _navActions: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }
    override val navActions = _navActions.asStateFlow()

    override fun navigate(navAction: NavigationAction) {
        _navActions.value = navAction
    }

    override fun back() {
        navigate(BackNavigation.back())
    }
}