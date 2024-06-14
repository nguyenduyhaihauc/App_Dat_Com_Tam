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
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.google.gson.Gson
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.navigation.RouterNameScreen
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomDialog
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomSnackbarHost
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import duyndph34554.fpoly.app_dat_com_tam.ui.utils.provideTypeRiceViewModel
import duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel.TypeRiceViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManageTypeRiceScreen(navController: NavController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val typeRiceViewModel = provideTypeRiceViewModel(navController.context)

    Scaffold(
        topBar = {
            CustomTopBar(onBackClick = {
                navController.popBackStack()
            }, image = R.drawable.logo_home, title = "Cum tứm đim")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(RouterNameScreen.AddTypeRice.router)
                },
                containerColor = Color("#FE724C".toColorInt())
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Type Rice",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { CustomSnackbarHost(snackbarHostState) }
    ) {
        ManageTypeRice(
            navController = navController,
            snackbarHostState = snackbarHostState,
            viewModel = typeRiceViewModel
        )
    }
}

@Composable
fun ManageTypeRice(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: TypeRiceViewModel
) {

    val typeRiceList by viewModel.typeRices.collectAsState(initial = listOf())

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            val existingItems = viewModel.typeRices.first()
            if (existingItems.isEmpty()) {
                viewModel.addTypeRice(TypeRice(typeRiceName = "Món chính"))
                viewModel.addTypeRice(TypeRice(typeRiceName = "Món thêm"))
                viewModel.addTypeRice(TypeRice(typeRiceName = "Topping"))
                viewModel.addTypeRice(TypeRice(typeRiceName = "Khác"))
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(color = Color(0xFF252121))
            .fillMaxSize()
            .padding(top = 110.dp),
    ) {
        items(typeRiceList) { typeRice ->
            TypeRiceItem(
                typeRice = typeRice,
                onUpdate = {
                    val typeRiceJson = Gson().toJson(typeRice)
                    navController.navigate("${RouterNameScreen.UpdateTypeRice.router}/$typeRiceJson")
                },
                onDelete = {
                    coroutineScope.launch {
                        viewModel.deleteTypeRice(typeRice)
                        snackbarHostState.showSnackbar("Đã xóa thành công", null, true)
                    }
                }
            )
        }
    }
}

@Composable
fun TypeRiceItem(typeRice: TypeRice, onUpdate: () -> Unit, onDelete: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomDialog(
            title = "Xác nhận xóa",
            message = "Bạn chắc chắn muốn xóa loại cơm này chứ?",
            onConfirm = {
                onDelete()
                showDialog = false
            },
            onCancel = { showDialog = false }
        )
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
                    IconButton(onClick = { showDialog = true }) {
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
