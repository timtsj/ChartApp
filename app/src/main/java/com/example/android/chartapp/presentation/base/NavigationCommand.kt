package com.example.android.chartapp.presentation.base

import androidx.navigation.NavDirections

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    object Back: NavigationCommand()
    data class BackTo(val destinationId: Int): NavigationCommand()
    object ToRoot: NavigationCommand()
}