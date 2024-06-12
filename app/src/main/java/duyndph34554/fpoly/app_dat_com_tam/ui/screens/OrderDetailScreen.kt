package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel.OrderViewModel
import java.text.DecimalFormat

data class ItemModel(val id: Int, val name: String, val price: String, val quantity: Int, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(navController: NavController, orderName: String, totalAmount: Double, status: Boolean) {
    val decimalFormat = DecimalFormat("#,###.##")
    var orderStatus by remember { mutableStateOf(status) }
    val orderViewModel: OrderViewModel = viewModel()

    val mainDishes = listOf(
        ItemModel(1, "Sườn bì", "56K", 2, R.drawable.suonbi),
        ItemModel(2, "Bì chả", "25K", 1, R.drawable.suonbi),
        ItemModel(3, "Bì trứng", "25K", 1, R.drawable.suonbi)
    )

    val sideDishes = listOf(
        ItemModel(1, "Sườn", "10K", 1, R.drawable.suonbi),
        ItemModel(2, "Sườn mỡ", "10K", 1, R.drawable.suonbi),
        ItemModel(3, "Trứng", "5K", 1, R.drawable.suonbi)
    )

    Scaffold(
        topBar = {
            CustomTopBar(
                onBackClick = { navController.popBackStack() },
                image = R.drawable.logo_home,
                title = "Cum tưm đim"
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFF252121))
                        .padding(16.dp)
                ) {
                    item {
                        Text(
                            text = "Thông tin đơn hàng",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Đơn hàng: $orderName",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tổng tiền: ${decimalFormat.format(totalAmount)} đ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Trạng thái: ${if (orderStatus) "Chấp nhận" else "Từ chối"}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500),
                            color = if (orderStatus) Color.Green else Color.Red
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (!orderStatus) {
                            Button(
                                onClick = {
                                    orderStatus = true
                                    orderViewModel.updateOrderStatus(orderName, true)
                                    navController.popBackStack()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Green,
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Text("Chấp nhận")
                            }
                        } else {
                            Button(
                                onClick = {
                                    orderStatus = false
                                    orderViewModel.updateOrderStatus(orderName, false)
                                    navController.popBackStack()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Text("Từ chối")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        Text(
                            text = "Món chính",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(mainDishes) { item ->
                        OrderItem(item = item)
                    }


                    item {
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Món thêm",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(sideDishes) { item ->
                        OrderItem(item = item)
                    }

                    item {
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))
                        OrderDetailInfo(
                            address = "Số nhà: 54, Đường: 14, Phường: Đông Hưng Thuận, Quận: 12, Thành phố: Hồ Chí Minh",
                            time = "Giờ: 13h45p",
                            phoneNumber = "SĐT: 0866704364",
                            mainDishCount = 4,
                            sideDishCount = 3,
                            toppingCount = 2,
                            otherCount = 2,
                            totalPrice = "133k"
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun OrderDetailInfo(
    address: String,
    time: String,
    phoneNumber: String,
    mainDishCount: Int,
    sideDishCount: Int,
    toppingCount: Int,
    otherCount: Int,
    totalPrice: String
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = address,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = time,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = phoneNumber,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Tổng số lượng món ăn: $mainDishCount",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Tổng số lượng món ăn thêm: $sideDishCount",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Tổng số lượng topping: $toppingCount",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Tổng số lượng khác: $otherCount",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Tổng tiền: $totalPrice",
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun OrderItem(item: ItemModel) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .background(color = Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = item.price,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Số lượng: ${item.quantity}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}


