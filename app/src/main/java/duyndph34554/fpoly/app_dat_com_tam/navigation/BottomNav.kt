package duyndph34554.fpoly.app_dat_com_tam.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.AddFoodScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.HomeScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.ManageFood
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.ManageScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.StatisticalScreen

import duyndph34554.fpoly.app_dat_com_tam.ui.screens.SupportScreen

@Composable
fun BottomBar(navCtrl: NavController) {

    val navController = rememberNavController()

    val selected = remember {
        mutableStateOf(RouterNameScreen.Home.router)
    }

    Scaffold (
        bottomBar = {
//            custom bottomBar
            BottomAppBar (
                containerColor = Color("#312C2C".toColorInt()),
                modifier = Modifier.height(92.dp)
            ) {

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    //            Home
                    Box (
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            IconButton(onClick = {
                                selected.value = RouterNameScreen.Home.router
                                navController.navigate(RouterNameScreen.Home.router) {
                                    popUpTo(0)
                                }
                            }
                            ) {
                                Image(painter = painterResource(id = if (selected.value == RouterNameScreen.Home.router) R.drawable.home_yellow else R.drawable.ic_home),
                                    contentDescription = "icon Home",
                                    modifier = Modifier.size(22.dp)
                                )
                            }

//                            Spacer(modifier = Modifier.height(3.dp))

                            Text(text = "Trang chủ",
                                color = if (selected.value == RouterNameScreen.Home.router) Color.Yellow else Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )

                        }
                    }

//                Statistical
                    Box (
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            IconButton(onClick = {
                                selected.value = RouterNameScreen.Statistical.router
                                navController.navigate(RouterNameScreen.Statistical.router) {
                                    popUpTo(0)
                                }
                            }
                            ) {
                                Image(painter = painterResource(id = if (selected.value == RouterNameScreen.Statistical.router) R.drawable.cart_yellow else R.drawable.ic_statistical),
                                    contentDescription = "icon Statistical",
                                    modifier = Modifier.size(22.dp)
                                )
                            }

//                            Spacer(modifier = Modifier.height(3.dp))

                            Text(text = "Thống kê",
                                color = if (selected.value == RouterNameScreen.Statistical.router) Color.Yellow else Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )

                        }
                    }

//                Manage
                    Box (
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            IconButton(onClick = {
                                selected.value = RouterNameScreen.Manage.router
                                navController.navigate(RouterNameScreen.Manage.router) {
                                    popUpTo(0)
                                }
                            }
                            ) {
                                Image(painter = painterResource(id = if (selected.value == RouterNameScreen.Manage.router) R.drawable.manage_yellow else R.drawable.ic_manage),
                                    contentDescription = "icon Manage",
                                    modifier = Modifier.size(22.dp)
                                )
                            }

//                            Spacer(modifier = Modifier.height(3.dp))

                            Text(text = "Quản lý",
                                color = if (selected.value == RouterNameScreen.Manage.router) Color.Yellow else Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )

                        }
                    }

//                Support
                    Box (
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            IconButton(onClick = {
                                selected.value = RouterNameScreen.Support.router
                                navController.navigate(RouterNameScreen.Support.router) {
                                    popUpTo(0)
                                }
                            }
                            ) {
                                Image(painter = painterResource(id = if (selected.value == RouterNameScreen.Support.router) R.drawable.support_yellow else R.drawable.ic_support),
                                    contentDescription = "icon Support",
                                    modifier = Modifier.size(22.dp)
                                )
                            }

//                            Spacer(modifier = Modifier.height(3.dp))

                            Text(text = "Hỗ trợ",
                                color = if (selected.value == RouterNameScreen.Support.router) Color.Yellow else Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400)
                            )

                        }
                    }
                }

                
            }
        }
    ) {
        NavHost(navController = navController,
            startDestination = RouterNameScreen.Home.router,
            modifier = Modifier.padding(it)
        ) {
            composable(RouterNameScreen.Home.router) {
                HomeScreen(navCtrl)
            }

            composable(RouterNameScreen.Statistical.router) {
                StatisticalScreen(navCtrl)
            }

            composable(RouterNameScreen.Manage.router) {
                ManageScreen(navCtrl)
            }

            composable(RouterNameScreen.Support.router) {
                SupportScreen(navCtrl)
            }

        }
    }
}