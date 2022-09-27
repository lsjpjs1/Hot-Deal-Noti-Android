package com.hotdealnoti.notification.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hotdealnoti.R
import com.hotdealnoti.data.local.App
import com.hotdealnoti.databinding.FragmentNotificationBinding
import com.hotdealnoti.notification.dto.NotificationDto
import com.hotdealnoti.notification.viewmodel.NotificationViewModel


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var mContext: Context
    private val viewModel:NotificationViewModel by viewModels()
    private val parentActivity by lazy {
        activity
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    private val notificationListAdapter by lazy {

        NotificationListAdapter(onNotificationClick)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        if (App.prefs.getValue("AUTH_TOKEN")=="") {
            findNavController().navigate(R.id.action_notificationFragment_to_loginFragment)
        }else{
            viewModel.getNotifications()
        }
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        binding.notificationFragment = this
        binding.lifecycleOwner = this
        binding.notificationListRV.also {
            val linearLayoutManager = LinearLayoutManager(parentActivity)
            it.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if(!it.canScrollVertically(1)){
                        viewModel.getNotifications(true)
                    }
                }
            })
            it.layoutManager = linearLayoutManager
            it.setHasFixedSize(false)
            it.adapter = notificationListAdapter
        }

        binding.notificationListSRL.setOnRefreshListener {
            viewModel.refreshNotification()
            binding.notificationListSRL.isRefreshing = false
        }

        viewModel.notifications.observe(viewLifecycleOwner, Observer {
            it?.let {

                notificationListAdapter.submitList(it.toList())
            }
        })

        viewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(mContext,it, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    fun goAddNotificationFragment() {
        findNavController().navigate(R.id.action_notificationFragment_to_addNotificationFragment)
    }


    val onNotificationClick = {notificationResponseDto: NotificationDto.Companion.NotificationResponseDto->
        when(notificationResponseDto.notificationType){
            "KEYWORD" -> {
                val action =
                    NotificationFragmentDirections.actionNotificationFragmentToShowHotDealFragment(notificationResponseDto.notificationItemId)
                findNavController().navigate(action)
            }
        }
    }

}