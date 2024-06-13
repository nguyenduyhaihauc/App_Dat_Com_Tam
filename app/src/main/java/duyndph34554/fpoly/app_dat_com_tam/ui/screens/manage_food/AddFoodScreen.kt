package duyndph34554.fpoly.app_dat_com_tam.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.rememberAsyncImagePainter
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.model.FoodModel
import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

fun saveBitmapToInternalStorage(
    context: Context,
    bitmap: Bitmap,
    imageName: String
): String {
    val directory = context.filesDir
    val file = File(directory, "$imageName.png")
    val fileOutputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
    fileOutputStream.close()
    return file.absolutePath
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddFoodScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onBackClick = { navController.popBackStack() },
                image = R.drawable.logo_home,
                title = "Cum tưm đim"
            )
        },
        content = {
            ContentAddFood(navController)
        },
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAddFood(navController: NavController) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var typeFood by remember {
        mutableStateOf("")
    }


    var giamonan by remember {
        mutableStateOf("")
    }

    var tenmonan by remember {
        mutableStateOf("")
    }

    var imageUrl by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()
    val context = navController.context

    val database = remember { MyDatabase.getInstance(context) }
    val foodDao = database.foodDao()

    val typeDao = database.typeRiceDao()
    val typeList by typeDao.getAllTypeRice().collectAsState(initial = emptyList())
    // Gán giá trị mặc định cho typeFood nếu typeList không rỗng
    if (typeList.isNotEmpty() && typeFood.isEmpty()) {
        typeFood = typeList[0].typeRiceName ?: ""
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }
            imageUrl = saveBitmapToInternalStorage(context, bitmap, tenmonan)
        }
    }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF252121))
                    .padding(top = 100.dp, start = 20.dp, end = 20.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { launcher.launch("image/*") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        elevation = null,
                        modifier = Modifier.size(205.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(205.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_addanh),
                                contentDescription = null,
                                modifier = Modifier.size(205.dp)
                            )

                            if (imageUrl.isNotEmpty()) {
                                Image(
                                    painter = rememberAsyncImagePainter(imageUrl),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(205.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                    }
                }

                // Loại món ăn
                Text(
                    text = "Loại món",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    TextField(
                        value = typeFood,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        typeList.forEach { typeRice ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = typeRice.typeRiceName ?: "Khác")
                                },
                                onClick = {
                                    typeFood = typeRice.typeRiceName ?: "Khác"
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Giá món ăn
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Giá",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = giamonan,
                    onValueChange = { giamonan = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(5.dp))
                )

                // Tên món ăn
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Tên món ăn",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = tenmonan,
                    onValueChange = { tenmonan = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(5.dp))
                )

                Spacer(modifier = Modifier.height(60.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (tenmonan.trim().isEmpty() || giamonan.trim().isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Yêu cầu nhập đầy đủ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                coroutineScope.launch {
                                    foodDao.insertFood(
                                        FoodModel(
                                            namefood = tenmonan,
                                            typefood = typeFood,
                                            pricefood = giamonan.toDoubleOrNull() ?: 0.0,
                                            imageurl = imageUrl,
                                            typeRiceId = 1
                                        )
                                    )
                                    Toast.makeText(
                                        context,
                                        "Thêm món ăn thành công",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#FFB703".toColorInt()),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Thêm",
                            fontSize = 20.sp,
                            fontWeight = FontWeight(700)
                        )
                    }
                }
            }
        }
    )
}
