package duyndph34554.fpoly.app_dat_com_tam.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import duyndph34554.fpoly.app_dat_com_tam.navigation.MainNavigation
import duyndph34554.fpoly.app_dat_com_tam.service.NetworkAwareScaffold
import duyndph34554.fpoly.app_dat_com_tam.service.NetworkViewModel

class MainActivity : ComponentActivity() {

    private val networkViewModel: NetworkViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkAwareScaffold(networkViewModel = networkViewModel) {
                MainNavigation()
            }
        }
    }
}


