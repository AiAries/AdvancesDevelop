package com.example.qrmodule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.qrmodule.zxing.ZXingScannerView

class MainActivity : AppCompatActivity(),ZXingScannerView.ResultHandler {

    private val zXingScannerView: ZXingScannerView
        get() {
            var mScannerView = ZXingScannerView(this)
            return mScannerView
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mScannerView = zXingScannerView
        setContentView(mScannerView)
    }

    override fun handleResult(rawResult: com.google.zxing.Result?) {
        Log.v("handleResult", rawResult!!.text)
        Log.v("handleResult", rawResult.barcodeFormat.toString())
        Toast.makeText(this, rawResult.text, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        zXingScannerView.setResultHandler(this)
        zXingScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zXingScannerView.stopCamera()
    }
}
