package com.example.batteryalarm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class BatteryViewModel : ViewModel() {

    // LiveData to hold battery percentage
    private val _batteryPercentage = MutableLiveData<Int>()
    val batteryPercentage: LiveData<Int> = _batteryPercentage

    // LiveData to hold battery status
    private val _batteryStatus = MutableLiveData<BatteryStatus>()
    val batteryStatus: LiveData<BatteryStatus> = _batteryStatus

    fun updateBatteryPercentage(percentage: Int) {
        _batteryPercentage.value = percentage
        _batteryStatus.value = when {
            percentage > 50 -> BatteryStatus.Full
            percentage in 0..49 -> BatteryStatus.Low
            else -> BatteryStatus.Full
        }
    }
}
enum class BatteryStatus {
    Full,
    Low
}