package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomSnackbarHost
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTextField
import duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel.AccountViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel: AccountViewModel = viewModel()

    Scaffold(
        snackbarHost = {
            CustomSnackbarHost(snackbarHostState)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F1B1E)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Đăng nhập",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.logosplash),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(200.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))
                CustomTextField(
                    label = "Tài khoản",
                    value = email.value,
                    onValueChange = { email.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    label = "Mật khẩu",
                    value = password.value,
                    onValueChange = { password.value = it },
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        val userName = email.value
                        val passWord = password.value
                        if (userName.isNotEmpty() && passWord.isNotEmpty()) {
                            coroutineScope.launch {
                                viewModel.getAccountByUsernameAndPassword(userName, passWord).collect { account ->
                                    if (account != null) {
                                        navController.navigate(RouterNameScreen.BottomScreen.router)
                                    } else {
                                        snackbarHostState.showSnackbar("Sai tên đăng nhập hoặc mật khẩu", null, true)
                                    }
                                }
                            }
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Không được để trống", null, true)
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5722)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                ) {
                    Text(text = "Đăng nhập", color = Color.White)
                }
            }
        }
    }
}
