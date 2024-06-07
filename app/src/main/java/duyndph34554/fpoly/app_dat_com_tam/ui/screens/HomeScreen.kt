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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel

val OrderArray = listOf(
    OrderModel("CT2E22E", 162.000, false),
    OrderModel("CT2E206", 157.000, false),
    OrderModel("CT2E23E", 160.000, false),
    OrderModel("CT2E12E", 160.000, false),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(navController: NavController ?= null) {
    val navController = navController ?: rememberNavController()

    Scaffold (
        topBar = {
            TopAppBar(navController = navController,
                iconLeft = R.drawable.logo_home,
                title = "Cum tứm đim",
                iconRight = R.drawable.logo_notifi)
        },
        content = {
            Box {
                Content(navController = navController)
            }
        },
        modifier = Modifier.padding(PaddingValues(0.dp))
    )

}

@Composable
fun TopAppBar(navController: NavController, iconLeft: Int, title: String, iconRight: Int? = null) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252121)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Row (
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = iconLeft),
                        contentDescription = "logo Home",
                        modifier = Modifier.size(57.dp)
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Text(text = title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight(700),
                        color = Color.White
                    )
                }
            }

            if (iconRight != null) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = iconRight),
                        contentDescription = "logo Notifi",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }

        }
        Divider(color = Color.Gray)
    }

}

@Composable
fun Content(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF252121))
            .padding(top = 50.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 25.dp)
            ) {
                Text(text = "Today: 19 - 05 - 2023",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Số lượng đơn: 2",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )
                    ) {
                        append("Doanh thu : ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Yellow,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )
                    ) {
                        append("320.000 đ")
                    }
                })
            }
        }


        LazyColumn (
            state = rememberLazyListState(),
        ) {
            items(OrderArray) {
                    order ->
                ItemOrder(nameTitle = order.nameOrder, totalAmount = order.totalAmount, status = order.status)
            }
        }


    }

}

@Composable
fun ItemOrder(nameTitle: String, totalAmount: Double, status: Boolean) {
    Box {
        Column (
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(110.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 0.dp)
                ) {
                    Text(text = "Đơn hàng: $nameTitle",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )


                    Box {
                        Text(text = "||",
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color.White,
                            modifier = Modifier.padding(end = 40.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = totalAmount.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color.White,
                            modifier = Modifier.padding(start = 35.dp)

                        )
                    }

                }
            }


            Box {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 0.dp)
                ) {
                    Text(text = "Trạng thái",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                    Text(text = if (status == false) "Từ chối" else "Chấp nhận",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = if (status == false) Color.Red else Color.Green
                    )
                }
            }

        }
    }
}