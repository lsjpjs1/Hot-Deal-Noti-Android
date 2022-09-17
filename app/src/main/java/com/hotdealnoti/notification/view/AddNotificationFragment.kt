package com.hotdealnoti.notification.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hotdealnoti.R
import com.hotdealnoti.databinding.FragmentAddNotificationBinding
import com.hotdealnoti.databinding.FragmentNotificationBinding
import com.hotdealnoti.notification.viewmodel.AddNotificationViewModel


class AddNotificationFragment : Fragment() {

    private val addNotificationViewModel:AddNotificationViewModel by viewModels()
    private lateinit var binding: FragmentAddNotificationBinding
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNotificationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.addNotificationFragment=this
        binding.viewModel = addNotificationViewModel

        addNotificationViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(mContext,it,Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }

}