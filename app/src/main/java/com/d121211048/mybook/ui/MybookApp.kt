package com.d121211048.mybook.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d121211048.mybook.AppDestinations
import com.d121211048.mybook.MybookNavHost
import com.d121211048.mybook.ui.screens.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MybookApp(
    modifier: Modifier = Modifier
) {
    // Notes: Set Nav Controller
    val navController = rememberNavController()
    // Notes: Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Notes: Get the name of the current screen check for null
    val currentScreen = AppDestinations.valueOf(
        backStackEntry?.destination?.route ?: AppDestinations.QueryScreen.name
    )

    // Notes: Boolean to check if we can nagigate back. Check stack
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                onNavigateUpClicked = { navController.navigateUp() }
            )
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background

        ) {
            MybookNavHost(
                navController = navController,
                modifier = modifier
            )
        }
    }
}