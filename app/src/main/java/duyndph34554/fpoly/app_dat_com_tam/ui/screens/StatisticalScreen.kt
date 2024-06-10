package duyndph34554.fpoly.app_dat_com_tam.ui.screens
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import java.util.Calendar

data class Order(val id: String, val date: String, val time: String, val status: String, val amount: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatisticalScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                iconLeft = R.drawable.logo_home,
                title = "Cum tứm đim"
            )
        },
        content = {
            Box() {
                Statistical()
            }

        },
    )
}

@Composable
fun Statistical() {
    var selectedTab by remember { mutableStateOf("Doanh thu") }
    var fromDate by remember { mutableStateOf("03/03/2023") }

    val calendar = Calendar.getInstance()
    val currentDate = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
    var toDate by remember { mutableStateOf(currentDate) }

    val orders = listOf(
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),
        Order("ID21220554", "03/03/2023", "09:53", "Hoàn thành", "57k"),

    )

    val context = LocalContext.current

    val fromDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            fromDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val toDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            toDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252121))
            .padding(top = 100.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF2F2D2D))
                .height(46.dp)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )  {
            Button(
                onClick = { selectedTab = "Doanh thu" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F2D2D)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Doanh thu",
                    color = if (selectedTab == "Doanh thu") Color(0xFFFFD700) else Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier
                .width(1.dp)
                .background(Color(0xFF000000))
                .fillMaxHeight()
            )
            Button(
                onClick = { selectedTab = "Biểu đồ" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F2D2D)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Biểu đồ",
                    color = if (selectedTab == "Biểu đồ") Color(0xFFFFD700) else Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { fromDatePickerDialog.show() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F2D2D)
                ),
                shape = RoundedCornerShape(6.dp), // Bo tròn nút
                border = BorderStroke(1.dp, Color(0xFF000000)),
                modifier = Modifier.weight(0.4f)

            ) {
                Text(
                    text = "Từ Ngày",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa hai nút
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .border(BorderStroke(1.dp, Color(0xFF000000)), shape = RoundedCornerShape(6.dp))
                    .background(Color(0xFF2F2D2D))
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .padding(start = 12.dp)
                ,
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = fromDate,
                    color = Color.White,

                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {toDatePickerDialog.show()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F2D2D)
                ),
                shape = RoundedCornerShape(6.dp), // Bo tròn nút
                border = BorderStroke(1.dp, Color(0xFF000000)),
                modifier = Modifier.weight(0.4f)

            ) {
                Text(
                    text = "Đến Ngày",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa hai nút
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .border(BorderStroke(1.dp, Color(0xFF000000)), shape = RoundedCornerShape(6.dp))
                    .background(Color(0xFF2F2D2D))
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .padding(start = 12.dp)
                ,
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = toDate,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(modifier = Modifier
            .height(3.dp)
            .background(Color(0xFF000000))
            .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (selectedTab == "Doanh thu") {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 12.dp, start = 12.dp)

            ) {
                items(orders) { order ->
                    OrderItem(order)
                }
            }
        } else if (selectedTab == "Biểu đồ") {
            BieuDo()
        }
    }
}
@Composable
fun OrderItem(order: Order) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)) // Bo tròn góc
            .background(Color(0xFF2C2C2C)) // Màu nền
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = order.id,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = order.date,
                    color = Color.White,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = order.time,
                    color = Color(0xFFFE724C),
                    fontSize = 12.sp
                )
            }
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = order.status,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = order.amount,
                    color = Color(0xFFFE724C),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun BieuDo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252121))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image(painter = painterResource(id = R.drawable.bieudo), contentDescription = "Bieu do",
           modifier = Modifier
               .fillMaxSize()

         )
        // Thêm nội dung của biểu đồ tại đây
    }
}


//hiển thị màn hình
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Statistical()
}
