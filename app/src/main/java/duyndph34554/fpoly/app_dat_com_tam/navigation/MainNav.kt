package duyndph34554.fpoly.app_dat_com_tam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.AddFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.LoginScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.ManageFood
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.OrderDetailScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.SplashScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_food.UpdateFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice.AddTypeRiceScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice.ManageTypeRiceScreen
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

        composable(RouterNameScreen.ManageTypeRice.router) { ManageTypeRiceScreen(navController) }
        composable(RouterNameScreen.AddTypeRice.router) { AddTypeRiceScreen(navController) }
        composable(
            "${RouterNameScreen.UpdateTypeRice.router}/{typeRice}",
            arguments = listOf(navArgument("typeRice") { type = NavType.StringType })
        ) { backStackEntry ->
            val typeRiceJson = backStackEntry.arguments?.getString("typeRice")
            val typeRice = Gson().fromJson(typeRiceJson, TypeRice::class.java)
            UpdateTypeRiceScreen(navController, typeRice)
        }

        composable(RouterNameScreen.ManageFood.router) { ManageFood(navController) }
        composable(RouterNameScreen.AddFood.router) { AddFoodScreen(navController) }
        composable(
            route = RouterNameScreen.UpdateFood.router,
            arguments = listOf(navArgument("foodId") {type = NavType.IntType})
        ) {
            backStackEntry ->
            val foodId = backStackEntry.arguments?.getInt("foodId") ?: 0
            UpdateFoodScreen(navController = navController, foodId = foodId)
        }

        composable(
            "orderDetail/{orderName}/{totalAmount}/{status}",
            arguments = listOf(
                navArgument("orderName") { type = NavType.StringType },
                navArgument("totalAmount") { type = NavType.StringType },
                navArgument("status") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val orderName = backStackEntry.arguments?.getString("orderName") ?: ""
            val totalAmount = backStackEntry.arguments?.getString("totalAmount")?.toDouble() ?: 0.0
            val status = backStackEntry.arguments?.getBoolean("status") ?: false
            OrderDetailScreen(navController, orderName, totalAmount, status)
        }

    }
}