package com.example.joketeller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.joketeller.ui.theme.Blue
import com.example.joketeller.ui.theme.DarkBlue
import com.example.joketeller.ui.theme.JokeTellerTheme
import com.example.joketeller.ui.theme.LightBlue
import com.example.joketeller.ui.theme.LightGreen
import com.example.joketeller.ui.theme.LightRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeTellerTheme {
                JokeWithTextAndButton()
            }
        }
    }
}


@Composable
fun JokeWithTextAndButton(modifier: Modifier = Modifier) {
    val jokes = listOf(R.string.joke_1,R.string.joke_2,R.string.joke_3,R.string.joke_4)
    var randomJokes by remember { mutableIntStateOf(R.string.jokes_will_appear_here) }
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            border = BorderStroke(2.dp, Brush.horizontalGradient(listOf(LightRed, LightGreen))),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = LightBlue),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = randomJokes),
                    fontSize = 24.sp,
                    style = TextStyle(brush = Brush.verticalGradient(listOf(Blue, DarkBlue))),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = {
                        randomJokes = jokes.random()
                        Toast.makeText(context, R.string.ha_ha, Toast.LENGTH_SHORT).show()
                              },
                    border = BorderStroke(2.dp,
                        Brush.horizontalGradient(listOf(LightRed, LightGreen))
                    ),
                    modifier = modifier.padding(top = 32.dp)
                ) {
                    Text(text = stringResource(id = R.string.ha_ha_me))
                }
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun JokeWithTextAndButtonPreview(){
    JokeWithTextAndButton()
}