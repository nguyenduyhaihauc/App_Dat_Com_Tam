package duyndph34554.fpoly.app_dat_com_tam.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import duyndph34554.fpoly.app_dat_com_tam.R
import kotlinx.coroutines.delay
    @Composable
    fun SplashScreen() {

        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.9f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logosplash),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxSize() // Adjust size as needed
            )
        }
    }

