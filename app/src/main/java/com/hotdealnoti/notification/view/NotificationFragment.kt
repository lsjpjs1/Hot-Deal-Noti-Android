package com.hotdealnoti.notification.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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
        binding.notificationFragment = this

        return binding.root
    }

    fun goAddNotificationFragment() {
        findNavController().navigate(R.id.action_notificationFragment_to_addNotificationFragment)
    }


}