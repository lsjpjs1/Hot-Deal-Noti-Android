package com.hotdealnoti.notification.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hotdealnoti.R
import com.hotdealnoti.databinding.FragmentAddNotificationBinding
import com.hotdealnoti.databinding.FragmentNotificationBinding


class AddNotificationFragment : Fragment() {


    private lateinit var binding: FragmentAddNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

}