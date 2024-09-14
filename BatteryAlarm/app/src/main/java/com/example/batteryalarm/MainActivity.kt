package com.example.batteryalarm

import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.batteryalarm.ui.BatteryStatus
import com.example.batteryalarm.ui.BatteryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val batteryViewModel = ViewModelProvider(this).get(BatteryViewModel::class.java)
        setContent {
            MaterialTheme {
                Surface {
                    BatteryApp()
                }
            }
        }

        val batteryPercentage = getBatteryPercentage()
        batteryViewModel.updateBatteryPercentage(batteryPercentage)
    }

    private fun getBatteryPercentage(): Int {
        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}

@Composable
fun BatteryApp(viewModel: BatteryViewModel = viewModel()) {
    val batteryStatus = viewModel.batteryStatus.observeAsState()

    val imageResource: Painter = when (batteryStatus.value) {
        BatteryStatus.Full -> painterResource(id = R.drawable.battery_full)
        BatteryStatus.Low -> painterResource(id = R.drawable.battery_low)
        else -> painterResource(id = R.drawable.battery_full)
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Image(painter = imageResource, contentDescription = "Battery Status", modifier = Modifier.size(200.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BatteryAppPreview() {
    BatteryApp()
}