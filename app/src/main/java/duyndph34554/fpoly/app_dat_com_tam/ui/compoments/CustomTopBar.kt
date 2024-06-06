package duyndph34554.fpoly.app_dat_com_tam.ui.compoments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import duyndph34554.fpoly.app_dat_com_tam.R
import duyndph34554.fpoly.app_dat_com_tam.ui.screens.TopAppBar

@Composable
fun CustomTopBar(onBackClick: () -> Unit, image: Int, title: String) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252121))
                .padding(start = 10.dp, top = 20.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "icon Back",
                    modifier = Modifier.size(26.dp),
                    tint = Color.White
                )
            }

            Image(painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(57.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
        }
        Divider(color = Color.Gray)
    }

}