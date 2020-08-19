package com.hemendra.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.JavascriptInterface;
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview.settings.javaScriptEnabled =true
        webview.settings.builtInZoomControls=true
        webview.webViewClient = myWebviewClient(this@MainActivity)
        btn.setOnClickListener {

           webview.loadUrl("https://${et.text.toString()}")
       }

         webview.addJavascriptInterface(this@MainActivity,"myinterface")

        btn_html.setOnClickListener {

            webview.loadUrl("file:///android_asset/a.html")
        }
    }

    @JavascriptInterface
    fun getData(text:String)
    {
        Toast.makeText(this@MainActivity,text,Toast.LENGTH_SHORT).show()
        et.text = text as Editable


    }
    class myWebviewClient(var context:Context):WebViewClient()
    {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Toast.makeText(context,"page loading started...",Toast.LENGTH_SHORT).show()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Toast.makeText(context,"page loaded successfully...!!!",Toast.LENGTH_SHORT).show()
        }
        override fun shouldOverrideUrlLoading(view: WebView?,url:String): Boolean {

            view?.loadUrl(url)

            return true
        }
    }




}