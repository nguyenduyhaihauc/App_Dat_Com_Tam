package duyndph34554.fpoly.app_dat_com_tam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.AddTypeRiceScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.ListTypeRiceScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.LoginScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.SplashScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.UpdateTypeRiceScreen


@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RouterNameScreen.Splash.router,
    ) {

        composable(RouterNameScreen.Splash.router) { SplashScreen(navController) }
        composable(RouterNameScreen.Login.router) { LoginScreen(navController) }
        composable(RouterNameScreen.AddTypeRice.router) { AddTypeRiceScreen() }
        composable(RouterNameScreen.UpdateTypeRice.router) { UpdateTypeRiceScreen() }
        composable(RouterNameScreen.ListTypeRice.router) { ListTypeRiceScreen() }

        composable(RouterNameScreen.BottomScreen.router) { BottomBar(navController) }

    }
}