package duyndph34554.fpoly.app_dat_com_tam.ui.compoments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import duyndph34554.fpoly.app_dat_com_tam.R


//------------------ TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (isPassword) {
            PasswordTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text(text = "Enter...") }, // Placeholder text
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White
                )
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        placeholder = { Text(text = "Nhập mật khẩu") }, // Placeholder text
        trailingIcon = {
            Row {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    modifier = Modifier
                        .size(28.dp),

                ) {
                    val eyeIcon = if (passwordVisible) {
                        painterResource(id = R.drawable.visibility)
                    } else {
                        painterResource(id = R.drawable.visibilityoff)
                    }
                    Icon(
                        painter = eyeIcon,
                        contentDescription = "Toggle password visibility",
                    )
                }
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.White
        )
    )
}


//------------------ Topbar
@Composable
fun CustomTopBar(onBackClick: () -> Unit, image: Int, title: String) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252121))
                .padding(start = 10.dp, top = 40.dp, bottom = 10.dp),
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


//------------------------ Dialog

@Composable
fun CustomDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(onClick = onCancel) {
                        Text("Cancel",color = Color(0xFFFFB703))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onConfirm, shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB703)),
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

//-------------------------- Custom snackbarhost
@Composable
fun CustomSnackbarHost(hostState: SnackbarHostState) {
    SnackbarHost(
        hostState = hostState,
        snackbar = { data: SnackbarData ->
            CustomSnackbar(data)
        }
    )
}

@Composable
fun CustomSnackbar(data: SnackbarData) {
    Snackbar(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.Black),
        action = {
            TextButton(onClick = { data.dismiss() }) {
                Text(
                    text = "Ẩn",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    ) {
        Text(
            text = data.visuals.message,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
