package com.example.integration_with_native

import io.flutter.embedding.android.FlutterActivity
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES

class MainActivity: FlutterActivity() {
  private val methodChannelBattery = "battery"
  private val eventChannelText = "call_events"
  private val methodChannelText = "call_method"
  private val intentName = "events"
  private val intentMessageId = "call"

  override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)
    MethodChannel(flutterEngine.dartExecutor.binaryMessenger, methodChannelBattery).setMethodCallHandler {
      call, result ->
		if (call.method == "getBatteryLevel") {
			val batteryLevel = getBatteryLevel()
 
			if (batteryLevel != -1) {
			  result.success(batteryLevel)
			} else {
			  result.error("UNAVAILABLE", "Battery level not available.", null)
			}
		 } else {
			result.notImplemented()
		 }
    }
  }

  private fun getBatteryLevel(): Int {
	val batteryLevel: Int
	if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
	  val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
	  batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
	} else {
	  val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
	  batteryLevel = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
	}

	return batteryLevel
 }

}