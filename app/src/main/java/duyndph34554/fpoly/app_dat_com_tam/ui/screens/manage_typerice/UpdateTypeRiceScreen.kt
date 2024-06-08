import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.room.database.TypeRiceDb
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.compoments.CustomTopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdateTypeRiceScreen(navController: NavController, typeRice: TypeRice) {
    Scaffold(
        topBar = {
            CustomTopBar(onBackClick = {
                navController.popBackStack()
            }, image = R.drawable.logo_home, title = "Cập nhật loại cơm tấm")
        },
        content = {
            UpdateTypeRice(navController = navController, initialTypeRice = typeRice)
        },
        modifier = Modifier.padding(0.dp)
    )
}

@Composable
fun UpdateTypeRice(navController: NavController, initialTypeRice: TypeRice) {
    var text by remember { mutableStateOf(TextFieldValue(initialTypeRice.typeRiceName ?: "")) }
    val coroutineScope = rememberCoroutineScope()
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
            placeholder = {
                Text(
                    text = "Nhập tên loại cơm tấm...",
                    fontSize = 13.sp,
                    color = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Button(
            onClick = {
                coroutineScope.launch {
                    if (text.text.isNotEmpty()) {
                        val updatedTypeRice = initialTypeRice.copy(typeRiceName = text.text)
                        updateTypeRice(navController, updatedTypeRice)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB703)),
            modifier = Modifier
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Cập nhật", color = Color.White, fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

private suspend fun updateTypeRice(navController: NavController, typeRice: TypeRice) {
    val dao = TypeRiceDb.getIntance(navController.context).typeRiceDao()
    dao.updateTypeRice(typeRice)
    navController.popBackStack()
}
