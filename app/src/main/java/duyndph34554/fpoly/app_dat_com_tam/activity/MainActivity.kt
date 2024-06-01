package duyndph34554.fpoly.app_dat_com_tam.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import duyndph34554.fpoly.app_dat_com_tam.navigation.BottomBar
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.HomeScreen
import duyndph34554.fpoly.app_dat_com_tam.ui.theme.App_Dat_Com_TamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            BottomBar(navCtrl = navController)
        }
    }
}