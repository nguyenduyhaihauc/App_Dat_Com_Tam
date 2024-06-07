package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.material3.Button

import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTypeRiceScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CustomTopBar(onBackClick = {
                navController.popBackStack()
            }, image = R.drawable.logo_home, title = "Cum tứm đim")
        },
        content = {
            AddTypeRice(navController = navController)
        },
        modifier = Modifier.padding(PaddingValues(top = 32.dp))
    )
}

@Composable
fun AddTypeRice(navController: NavController) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF252121)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "Nhập loại cơm tấm...", fontSize = 13.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB703)),
            modifier = Modifier
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Thêm", color = Color.White, fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
