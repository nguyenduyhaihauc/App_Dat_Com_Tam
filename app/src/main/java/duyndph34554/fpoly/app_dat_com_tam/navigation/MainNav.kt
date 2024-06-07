package duyndph34554.fpoly.app_dat_com_tam.navigation

import AddTypeRiceScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.AddFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.DeleteFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice.ListTypeRiceScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.LoginScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.SplashScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.UpdateFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice.UpdateTypeRiceScreen


@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RouterNameScreen.Splash.router,
    ) {

        composable(RouterNameScreen.Splash.router) { SplashScreen(navController) }
        composable(RouterNameScreen.Login.router) { LoginScreen(navController) }

        composable(RouterNameScreen.BottomScreen.router) { BottomBar(navController) }
        composable(RouterNameScreen.AddTypeRice.router) { AddTypeRiceScreen(navController) }
        composable(RouterNameScreen.UpdateTypeRice.router) { UpdateTypeRiceScreen(navController) }
        composable(RouterNameScreen.ListTypeRice.router) { ListTypeRiceScreen(navController) }

        composable(RouterNameScreen.AddFood.router) { AddFoodScreen(navController) }
        composable(RouterNameScreen.UpdateFood.router) { UpdateFoodScreen(navController)}
        composable(RouterNameScreen.DeleteFood.router) { DeleteFoodScreen(navController)}
    }
}