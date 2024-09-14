package com.example.infinitydogs

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.infinitydogs.ui.theme.InfinityDogsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// https://dog.ceo/api/breeds/image/random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfinityDogsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DogImage(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DogImage(modifier: Modifier = Modifier) {
    var dog by remember { mutableStateOf<Dog?>(null) }
    val context = LocalContext.current
    var isLoaded by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        getDogImage {
            if (it != null) {
                dog = it
            } else {
                Toast.makeText(context, "Error loading image", Toast.LENGTH_SHORT).show()
            }
            isLoaded = false
        }
    }

    if (!isLoaded)
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(dog?.imageLink)
                .crossfade(700)
                .placeholder(R.drawable.downward_24)
                .error(R.drawable.baseline_error_24)
                .build(),
            contentDescription = "Dog Image",
            modifier = modifier.fillMaxSize()
        )
}



// Better put in Viewmodel if no repo is used
private fun getDogImage(
    onDogImageLoaded: (Dog?) -> Unit
) {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val callable = retrofit.create(DogAPICallable::class.java)
    callable.getRandomDog().enqueue(object : Callback<Dog> {
        override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
            val dog = response.body()
            onDogImageLoaded(dog!!)
        }
        override fun onFailure(call : Call<Dog>, t: Throwable){
            Log.d("trace", "Error : ${t.message}")
            onDogImageLoaded(null)
        }
    })
}