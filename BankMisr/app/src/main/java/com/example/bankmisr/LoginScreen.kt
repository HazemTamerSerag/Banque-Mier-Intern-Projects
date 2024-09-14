package com.example.bankmisr

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankmisr.ui.theme.BlueGray
import com.example.bankmisr.ui.theme.lightRed
import java.util.Locale

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isArabic by remember { mutableStateOf(false) }

    val isLoginEnabled = username.isNotEmpty() && password.any { it.isDigit() }
    val darkModeBackground = if (isSystemInDarkTheme()) BlueGray else Color.White

    if (isArabic) stringResource(R.string.arabic) else stringResource(R.string.arabic)
    val currentLanguage = if (isArabic) "ar" else "en"


    CompositionLocalProvider(LocalContext provides LocalContext.current.createConfigurationContext(
        Configuration(LocalContext.current.resources.configuration).apply {
            setLocale(Locale(currentLanguage))
        }
    )) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = darkModeBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()) // Added scroll capability
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bm_icon),
                        contentDescription = stringResource(R.string.bankmiserlogo),
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = stringResource(R.string.arabic), // Updated for clarity
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier.clickable { isArabic = !isArabic }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(R.string.username)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.password)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) R.drawable.openeye else R.drawable.eye
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = if (passwordVisible) "Open eye" else "Closed eye",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.forgot_username_password),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.Start),
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle login action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLoginEnabled) Color.Red else lightRed
                    ),
                    enabled = isLoginEnabled,
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(stringResource(R.string.login))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.need_help),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = stringResource(R.string.contact_us),
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf(
                        Pair(R.drawable.our_products, R.string.our_products),
                        Pair(R.drawable.exchange_rate, R.string.exchange_rate),
                        Pair(R.drawable.security_tips, R.string.security_tips),
                        Pair(R.drawable.nearest_branch_or_atm, R.string.nearest_branch_or_atm)
                    ).forEach { (iconId, textId) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.widthIn(min = 0.dp, max = 75.dp)
                        ) {
                            Image(
                                painter = painterResource(id = iconId),
                                contentDescription = stringResource(textId),
                                modifier = Modifier.size(55.dp)
                            )
                            Text(
                                text = stringResource(textId),
                                fontSize = 12.sp,
                                maxLines = 3,
                                overflow = TextOverflow.Clip,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}