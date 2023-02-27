package com.example.howtoopencamerakotlin

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback

class MainActivity : AppCompatActivity() {
    private lateinit var codeScanner:CodeScanner
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scannerView = findViewById<CodeScannerView>(R.id.QRScanner)
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                val permissionList = arrayOf(Manifest.permission.CAMERA)
                requestPermissions(permissionList,1)
        }

        codeScanner = CodeScanner(this,scannerView )
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.isAutoFocusEnabled=true
        codeScanner.isFlashEnabled=false
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val intent = Intent(this,DisplayLink::class.java)
                    intent.putExtra("myMessage",it.toString())
                    startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }






}