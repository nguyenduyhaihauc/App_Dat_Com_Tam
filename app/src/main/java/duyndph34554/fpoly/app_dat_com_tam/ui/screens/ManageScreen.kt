package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManageScreen (navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(navController = navController,
                iconLeft = R.drawable.logo_home,
                title = "Cum tứm đim")
        },
        content = {
                Manage(navController = navController)
        },
        modifier = Modifier.padding(PaddingValues(0.dp))
    )
}


@Composable
fun Manage(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF252121))
            .padding(top = 100.dp)
    ) {
        Button(onClick = {
                         navController.navigate(RouterNameScreen.ManageTypeRice.router)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF252121)
            )
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(id = R.drawable.logo_home),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = "Quản lý loại món ăn",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
                         navController.navigate(RouterNameScreen.ManageFood.router)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF252121)
            )
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(id = R.drawable.logo_home),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = "Quản lý món ăn",
                    fontSize = 17.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White
                )
            }
        }

    }

}

@Preview
@Composable
fun show(){
    val navController = rememberNavController()
    Manage(navController = navController)
}