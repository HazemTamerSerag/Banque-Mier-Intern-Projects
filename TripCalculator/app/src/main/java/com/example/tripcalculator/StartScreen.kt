package com.example.tripcalculator

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(modifier: Modifier = Modifier){

    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column (
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.road) , contentDescription = "Road"
            , modifier = modifier.size(300.dp)
        )
        Button(
            onClick = {
                MediaPlayer
                    .create(context, R.raw.car_horn)
                    .start()
            },
            modifier = modifier.align(Alignment.CenterHorizontally)
        ){
            Text(text = "Start")
        }
        Image(painter = painterResource(id =R.drawable.car),
            contentDescription = "Car",
        modifier = modifier.size(300.dp)
            .offset(screenWidth.div(2))
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun StartScreenPreview() {
    StartScreen()
}