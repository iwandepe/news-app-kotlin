package com.iwandepe.newsapp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.iwandepe.newsapp2.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    var url: String = "www.google.com"
    val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentWebViewBinding> (inflater, R.layout.fragment_web_view, container, false)

        val webView: WebView = binding.webView
        url = args.myArg
        webView.loadUrl(url)

        // Enable Javascript
        val webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(WebViewClient())
        return binding.root
    }
}