package com.hotdealnoti.notification.view

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
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.hotdealnoti.R
import com.hotdealnoti.databinding.FragmentNotificationBinding
import com.hotdealnoti.databinding.FragmentShowHotDealBinding
import com.hotdealnoti.home.view.HomeFragment
import com.hotdealnoti.notification.viewmodel.ShowHotDealViewModel


class ShowHotDealFragment : Fragment() {
    private lateinit var binding: FragmentShowHotDealBinding
    private val args: ShowHotDealFragmentArgs by navArgs()
    private val viewModel: ShowHotDealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHotDealLink(args.hotDealId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowHotDealBinding.inflate(inflater, container, false)
        binding.showHotDealFragment = this
        binding.lifecycleOwner = this
        webViewSetting()
        viewModel.hotDealLink.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.showHotDealWV.loadUrl(it)
            }
        })
        Log.d("oncreate", "oncreate")
        return binding.root
    }


    fun webViewSetting() {
        binding.showHotDealWV.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportMultipleWindows(true)
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
        }
        binding.showHotDealWV.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }


    }

}