package duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import duyndph34554.fpoly.app_dat_com_tam.ui.utils.provideTypeRiceViewModel
import duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel.TypeRiceViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTypeRiceScreen(navController: NavController) {
    val typeRiceViewModel = provideTypeRiceViewModel(navController.context)

    Scaffold(
        topBar = {
            CustomTopBar(onBackClick = {
                navController.popBackStack()
            }, image = R.drawable.logo_home, title = "Cum tứm đim")
        },
        content = {
            AddTypeRice(navController = navController,typeRiceViewModel)
        },
        modifier = Modifier.padding(0.dp)
    )
}

@Composable
fun AddTypeRice(navController: NavController,typeRiceViewModel: TypeRiceViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF252121)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "Nhập loại cơm tấm...", fontSize = 16.sp, color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (text.text.isNotEmpty()) {
                    coroutineScope.launch {
                        typeRiceViewModel.addTypeRice(TypeRice(typeRiceName = text.text))
                        navController.popBackStack()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB703)),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Thêm", color = Color.White, fontSize = 16.sp)
        }
    }
}
