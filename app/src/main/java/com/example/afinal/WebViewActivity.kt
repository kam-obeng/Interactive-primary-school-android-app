package com.example.afinal



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView: WebView = findViewById(R.id.webview)
        val url = intent.getStringExtra("URL")

        // Enable JavaScript (optional)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = WebViewClient()

        // Load the URL
        webView.loadUrl(url!!)
    }
}
