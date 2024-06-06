package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddFoodScreen(navController: NavController) {
    Scaffold (
        topBar = {
            CustomTopBar(onBackClick = { navController.popBackStack() },
                image = R.drawable.logo_home,
                title = "Cum tưm đim")
        },
        content = {
            ContentAddFood()
        }
    )
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentAddFood() {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Món chính")
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF252121))
            .padding(top = 100.dp)
    ) {

        Image(painter = painterResource(id = R.drawable.img_addanh),
            contentDescription = null,
            modifier = Modifier.size(205.dp)
        )
        
        Text(text = "Loại món",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color.White
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(text = { Text(text = "Món chính") },
                onClick = {
                    selectedItem = "Món chính"
                    expanded = false
                }
            )

            DropdownMenuItem(text = { Text(text = "Món them") },
                onClick = {
                    selectedItem = "Món them"
                    expanded = false
                }
            )

            DropdownMenuItem(text = { Text(text = "Topping") },
                onClick = {
                    selectedItem = "Topping"
                    expanded = false
                }
            )

            DropdownMenuItem(text = { Text(text = "Khac") },
                onClick = {
                    selectedItem = "Khac"
                    expanded = false
                }
            )
        }

    }
}