package duyndph34554.fpoly.app_dat_com_tam.service

import android.annotation.SuppressLint
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NetworkAwareScaffold(
    networkViewModel: NetworkViewModel,
    content: @Composable () -> Unit
) {
    val networkState by networkViewModel.networkState.observeAsState(initial = true)
    var showNoConnection by remember { mutableStateOf(!networkState) } // k có internet
    var showReconnected by remember { mutableStateOf(false) }// có kết nối lại
    var firstCheckDone by remember { mutableStateOf(false) } // state lần check khi khởi động ứng dụng

    val coroutineScope = rememberCoroutineScope()

    // hàm chạy khi khởi động ứng dụng, nó sẽ render lại nếu state network thay đổi
    LaunchedEffect(networkState) {
        // gọi hàm xử lý khi state network thay đổi
        handleNetworkStateChange(
            networkState = networkState,
            firstCheckDone = firstCheckDone,
            setFirstCheckDone = { firstCheckDone = it },
            setShowNoConnection = { showNoConnection = it },
            setShowReconnected = { showReconnected = it },
            coroutineScope = coroutineScope
        )
    }

    Scaffold(
        content = { content() },
        snackbarHost = {
            SnackbarHost(hostState = SnackbarHostState()) { snackData ->
                Snackbar(
                    snackbarData = snackData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                )
            }
        },
        bottomBar = {
            if (showNoConnection) {
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("No connection", color = Color.Red)
                    }
                }
            } else if (showReconnected) {
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Connected", color = Color.Green)
                    }
                }
            }
        }
    )
}

// hàm xử lí logic thao tác khi state network thay đổi
fun handleNetworkStateChange(
    networkState: Boolean,
    firstCheckDone: Boolean,
    setFirstCheckDone: (Boolean) -> Unit,
    setShowNoConnection: (Boolean) -> Unit,
    setShowReconnected: (Boolean) -> Unit,
    coroutineScope: CoroutineScope
) {
    if (!firstCheckDone) {
        // xử lí logic khi lần check đầu tiên
        setFirstCheckDone(true)
        setShowNoConnection(!networkState)
    } else {
        // xử lí logic khi state network thay đổi
        if (!networkState) {
            setShowNoConnection(true)
            setShowReconnected(false)
        } else {
            // xử lí logic khi có kết nối lại
            setShowNoConnection(false)
            setShowReconnected(true)
            coroutineScope.launch {
                delay(2000)
                setShowReconnected(false)
            }
        }

    }
}
