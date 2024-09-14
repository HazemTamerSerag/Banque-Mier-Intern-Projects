package com.example.profileapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profileapp.ui.theme.ProfileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileAppTheme {
                ProfileDesign(stringResource(R.string.name),
                    stringResource(R.string.job), stringResource(R.string.email),
                    stringResource(R.string.number)
                )
            }
        }
    }
}


@Composable
fun ProfileDesign(name : String ,job : String,email : String,number: String , modifier: Modifier = Modifier ) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.profile_bg),
                contentScale = ContentScale.Crop,
                alpha = 0.4f
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .border(
                    BorderStroke(4.dp, Color.Black),
                    CircleShape
                )
                .background(color = Color.Gray, shape = CircleShape)
                .padding(4.dp)
                .clip(CircleShape)
        )
        Text(text = name, fontSize = 50.sp, lineHeight = 60.sp)
        Text(text = job, fontSize = 32.sp, textAlign = TextAlign.Center)

        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(text = email, modifier = Modifier.padding(end = 8.dp), textDecoration = TextDecoration.Underline)
            Text(text = number, textDecoration = TextDecoration.Underline)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, locale = "ar")
@Composable
fun ProfileDesignPreview(){
    ProfileDesign(stringResource(R.string.name),stringResource(R.string.job),stringResource(R.string.email),stringResource(R.string.number))
}