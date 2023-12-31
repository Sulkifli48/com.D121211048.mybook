package com.d121211048.mybook

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d121211048.mybook.ui.screens.detail_screen.DetailScreen
import com.d121211048.mybook.ui.screens.detail_screen.DetailsViewModel
import com.d121211048.mybook.ui.screens.favorite_screen.FavoritesScreen
import com.d121211048.mybook.ui.screens.query_screen.QueryScreen
import com.d121211048.mybook.ui.screens.query_screen.QueryViewModel
import com.d121211048.mybook.ui.screens.menu_screen.MenuScreen

@Composable
fun MybookNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel : QueryViewModel = viewModel(factory = QueryViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = AppDestinations.MenuScreen.name,
        modifier = modifier
    ) {

        composable(route = AppDestinations.MenuScreen.name) {
            MenuScreen(
                onSearchClick = {
                    navController.navigate(AppDestinations.QueryScreen.name)
                },
                onFavClick = {
                    navController.navigate(AppDestinations.FavoriteScreen.name)
                }
            )
        }

        composable(route = AppDestinations.QueryScreen.name) {
            QueryScreen(
                viewModel = viewModel,
                retryAction = { viewModel.getBooks() },
                onDetailsClick = {
                    viewModel.selectedBookId = it.id
                    navController.navigate(AppDestinations.DetailScreen.name)
                },
            )
        }

        composable(route = AppDestinations.FavoriteScreen.name) {
            FavoritesScreen(
                viewModel = viewModel,
                retryAction = { viewModel.getBooks() },
                mybookUiState = viewModel.favoritesfUiState
            )
        }

        composable(route = AppDestinations.DetailScreen.name) {
            val detailViewModel : DetailsViewModel = viewModel(factory = DetailsViewModel.Factory)
            detailViewModel.getBook(viewModel.selectedBookId)

            DetailScreen(
                viewModel = detailViewModel,
                retryAction = { detailViewModel.getBook(viewModel.selectedBookId) },
            )
        }
    }
}