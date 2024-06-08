package duyndph34554.fpoly.app_dat_com_tam.ui.screens.manage_typerice

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.available.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.room.database.TypeRiceDb
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomDialog
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomSnackbarHost
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListTypeRiceScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            CustomTopBar(onBackClick = {
                navController.popBackStack()
            }, image = R.drawable.logo_home, title = "Cum tứm đim")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(RouterNameScreen.AddTypeRice.router)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Type Rice")
            }
        },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { CustomSnackbarHost(snackbarHostState) }
        ) {
        Box(modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize()
            .background(color = Color(0xFF252121)) ,
        ) {
            ListTypeRice(navController = navController, snackbarHostState = snackbarHostState)
        }
    }
}

@Composable
fun ListTypeRice(navController: NavController,snackbarHostState: SnackbarHostState) {
    val context = navController.context
    val database = remember { TypeRiceDb.getIntance(context) }
    val typeRiceDao = database.typeRiceDao()

    var typeRiceList by remember { mutableStateOf(listOf<TypeRice>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            typeRiceDao.getAllTypeRice().collect { list ->
                typeRiceList = list
            }
        }

        coroutineScope.launch {
            val existingItems = typeRiceDao.getAllTypeRice().first()
            if (existingItems.isEmpty()) {
                typeRiceDao.insertTypeRice(
                    TypeRice(typeRiceName = "Jasmine Rice"),
                    TypeRice(typeRiceName = "Basmati Rice"),
                    TypeRice(typeRiceName = "Sushi Rice")
                )
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(typeRiceList) { typeRice ->
            TypeRiceItem(
                typeRice = typeRice,
                onUpdate = {
                    val typeRiceJson = Gson().toJson(typeRice)
                    navController.navigate("${RouterNameScreen.UpdateTypeRice.router}/$typeRiceJson")                },
                onDelete = {
                    coroutineScope.launch {

                        typeRiceDao.deleteTypeRice(typeRice)
                        typeRiceList = typeRiceList.filter { it.typeRiceId != typeRice.typeRiceId }

                        snackbarHostState.showSnackbar("Đã xóa thành công",null,true)
                    }
                }
            )
        }
    }
}

@Composable
fun TypeRiceItem(typeRice: TypeRice, onUpdate: () -> Unit, onDelete: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

if(showDialog){
    CustomDialog(title = "Xác nhận xóa", message ="Bạn chắc chắn muốn xóa loại cơm này chứ?" ,
        onConfirm = {onDelete()}, onCancel = { showDialog=false})
}

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2F2D2D))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = typeRice.typeRiceName ?: "", color = Color.White)

                Row {
                    IconButton(onClick = onUpdate) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {showDialog=true}) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}
