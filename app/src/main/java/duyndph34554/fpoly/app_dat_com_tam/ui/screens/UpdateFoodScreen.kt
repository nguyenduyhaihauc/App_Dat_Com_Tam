package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdateFoodScreen(navController: NavController) {
    Scaffold (
        topBar = {
            CustomTopBar(onBackClick = { navController.popBackStack() },
                image = R.drawable.logo_home,
                title = "Cum tưm đim")
        },
        content = {
            ContentUpdateFood()
        },
        modifier = Modifier.padding(PaddingValues(top = 32.dp))
    )
}

@Composable
fun ContentUpdateFood() {

}