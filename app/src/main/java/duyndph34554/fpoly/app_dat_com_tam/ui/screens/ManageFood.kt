package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManageFood(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(navController = navController,
                iconLeft = R.drawable.logo_home,
                title = "Com tưm đim")
        },
        content = {
            ContentManageFood(navController = navController)
        }
    )
}

@Composable
fun ContentManageFood(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF252121))
            .padding(top = 100.dp)
    ) {
        ItemManage(image = R.drawable.logo_home, title = "Thêm món ăn") {
            navController.navigate(RouterNameScreen.AddFood.router)
        }

        ItemManage(image = R.drawable.logo_home, title = "Sửa món ăn") {
            navController.navigate(RouterNameScreen.UpdateFood.router)
        }

        ItemManage(image = R.drawable.logo_home, title = "Xóa món ăn") {
            navController.navigate(RouterNameScreen.DeleteFood.router)
        }
    }

}

@Composable
fun ItemManage(image: Int, title: String, onClickItem: () -> Unit) {
    Button(onClick = { onClickItem() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF252121)
        )
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
        }
    }
}