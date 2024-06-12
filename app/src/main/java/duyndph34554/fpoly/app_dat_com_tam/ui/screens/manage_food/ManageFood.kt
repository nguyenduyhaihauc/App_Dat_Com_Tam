package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.rememberImagePainter
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.model.FoodModel
import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManageFood(navController: NavController) {


    Scaffold (
        topBar = {
            CustomTopBar(onBackClick = { navController.popBackStack() },
                image = R.drawable.logo_home,
                title = "Com tum diem")
        },
        content = {
            ContentManageFood(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(RouterNameScreen.AddFood.router)
            },
                containerColor = Color("#FE724C".toColorInt())
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "add food",
                    tint = Color.White)
            }
        }
    )
}

@Composable
fun ContentManageFood(navController: NavController) {

    val context = navController.context

    val database = remember { MyDatabase.getInstance(context) }
    val foodDao = database.foodDao()

    val foodList by foodDao.getAllFood().collectAsState(initial = emptyList())
    var foods by remember {
        mutableStateOf(foodList)
    }
    fun refreshFoodList() {
        foods = foodList
    }

    LaunchedEffect(foodList) {
        refreshFoodList()
    }

    LazyColumn (
        modifier = Modifier
            .background(color = Color("#252121".toColorInt()))
            .fillMaxSize()
            .padding(top = 120.dp)
    ) {
        items(foods) {food->
            FoodItem(food = food, navController,
//                Xoa mon an ngay
                onDelete = {foods = foods.filter { it.foodid != food.foodid }}
            )
        }
    }
}

@Composable
fun FoodInfoDialog(food: FoodModel, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Thông tin món ăn",
                    fontSize = 30.sp,
                    fontWeight = FontWeight(700))
            }

        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(food.imageurl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = "Tên: ${food.namefood}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = "Loại: ${food.typefood}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = "Giá: ${food.pricefood}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500)
                )
            }
        },
        confirmButton = {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#FFB703".toColorInt()),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(text = "Đóng",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700))
                }
            }

        }
    )
}

@Composable
fun FoodItem(food: FoodModel, navController: NavController, onDelete: () -> Unit) {
    val priceFormat = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
    val priceInVND = priceFormat.format(food.pricefood)
    var showDialog by remember {
        mutableStateOf(false)
    }

    var foodToDelete by remember {
        mutableStateOf<FoodModel?>(null)
    }

    var showDetalFood by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    val context = navController.context

    val database = remember { MyDatabase.getInstance(context) }
    val foodDao = database.foodDao()
    if (showDialog && foodToDelete != null) {
        AlertDialog(onDismissRequest = {showDialog = false},
            title = {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Thông Báo",
                        fontSize = 25.sp,
                        fontWeight = FontWeight(700)
                    )
                }},
            text = {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Bạn có chắc chắn muốn xóa món ăn này không?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500)
                    )
                }
                },
            confirmButton = {
                Button(onClick = {
                    coroutineScope.launch {
                        foodDao.deleteFood(foodToDelete!!)
                        onDelete()
                        showDialog = false
                        foodToDelete = null
                        Toast.makeText(context, "Delete Food Successfully", Toast.LENGTH_SHORT).show()
                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#FFB703".toColorInt()),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Xác nhận")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    foodToDelete = null
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color("#FFB703".toColorInt())
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Cancel", fontWeight = FontWeight(600))
                }
            }
        )
    }

    if (showDetalFood) {
        FoodInfoDialog(food = food, onDismiss = { showDetalFood = false })
    }
    Box {
        Row (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(
                    color = Color("#2F2D2D".toColorInt()),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { showDetalFood = true },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
//                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Image(painter = rememberImagePainter(food.imageurl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(15.dp))

                Column (
//                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
//                    Text(text = "Id: ${food.foodid}")
                    Text(text = "Name: ${food.namefood}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400)
                    )
                    Text(text = "Price: $priceInVND",
                        color = Color("#FE724C".toColorInt()),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400)
                    )
                }
            }

            Column (
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                IconButton(onClick = {
                    navController.navigate(RouterNameScreen.UpdateFoodWithId(food.foodid))
                }) {
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                IconButton(onClick = {
                    foodToDelete = food
                    showDialog = true
                }) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

            }

        }
    }

}
