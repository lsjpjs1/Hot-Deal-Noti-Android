package com.hotdealnoti.home.view

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import com.hotdealnoti.R
import com.hotdealnoti.databinding.FragmentHomeBinding
import com.hotdealnoti.home.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var newWebView: WebView
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("oncreate", "oncreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        webViewSetting()
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.homeWebView.isGone) {
                    newWebView.loadUrl("javascript:window.close();")
                    binding.homeWebView.isGone = false
                } else {
                }
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressedCallback.remove()
    }

    fun webViewSetting() {
        binding.homeWebView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportMultipleWindows(true)
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
        }
        binding.homeWebView.loadUrl(HOME_PAGE_URL)

        binding.homeWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        binding.homeWebView.webChromeClient = object : WebChromeClient() {
            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {
                view?.let { v ->
                    newWebView = WebView(v.context)
                    newWebView.settings.apply {
                        javaScriptEnabled = true
                        setSupportZoom(true)
                        builtInZoomControls = true
                        setSupportMultipleWindows(true)
                        javaScriptCanOpenWindowsAutomatically = true
                        domStorageEnabled = true
                    }
                    newWebView.webViewClient = WebViewClient()
                    newWebView.webChromeClient = object : WebChromeClient() {
                        override fun onCloseWindow(window: WebView?) {
                            binding.webViewFrameLayout.removeView(window)
                            window?.let { it.destroy() }
                        }
                    }

                    binding.homeWebView.isGone = true
                    binding.webViewFrameLayout.addView(newWebView)

                    resultMsg?.let {
                        (it.obj as WebView.WebViewTransport).webView = newWebView
                        it.sendToTarget()
                    }


                }

                return true
            }
        }
    }

    companion object {
        const val HOME_PAGE_URL: String = "https://www.whendiscount.com"
    }
}