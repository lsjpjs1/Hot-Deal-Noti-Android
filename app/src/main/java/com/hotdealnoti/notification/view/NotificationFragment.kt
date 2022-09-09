package com.hotdealnoti.notification.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hotdealnoti.R
import com.hotdealnoti.data.local.App
import com.hotdealnoti.databinding.FragmentHomeBinding
import com.hotdealnoti.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        if (App.prefs.getValue("AUTH_TOKEN")=="") {
            findNavController().navigate(R.id.action_notificationFragment_to_loginFragment)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }



}