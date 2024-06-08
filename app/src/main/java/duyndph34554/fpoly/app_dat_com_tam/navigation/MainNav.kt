package duyndph34554.fpoly.app_dat_com_tam.navigation

import AddTypeRiceScreen
import UpdateTypeRiceScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_food.AddFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.LoginScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_food.ManageFood
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.SplashScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_food.UpdateFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice.ManageTypeRiceScreen


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

        composable(
            "${RouterNameScreen.UpdateTypeRice.router}/{typeRice}",
            arguments = listOf(navArgument("typeRice") { type = NavType.StringType })
        ) { backStackEntry ->
            val typeRiceJson = backStackEntry.arguments?.getString("typeRice")
            val typeRice = Gson().fromJson(typeRiceJson, TypeRice::class.java)
            UpdateTypeRiceScreen(navController, typeRice)
        }
        composable(RouterNameScreen.ManageTypeRice.router) { ManageTypeRiceScreen(navController) }
        composable(RouterNameScreen.ManageFood.router) { ManageFood(navController) }

        composable(RouterNameScreen.AddFood.router) { AddFoodScreen(navController) }
        composable(RouterNameScreen.UpdateFood.router) { UpdateFoodScreen(navController) }
    }
}