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
import androidx.recyclerview.widget.LinearLayoutManager
import com.hotdealnoti.databinding.FragmentAddNotificationBinding
import com.hotdealnoti.notification.viewmodel.AddNotificationViewModel
import kotlinx.android.synthetic.main.fragment_add_notification.*


class AddNotificationFragment : Fragment() {

    private val addNotificationViewModel: AddNotificationViewModel by viewModels()
    private lateinit var binding: FragmentAddNotificationBinding
    private lateinit var mContext: Context

    private val parentActivity by lazy {
        activity
    }

    private val notificationKeywordListAdapter by lazy {
        val onClickDeleteKeywordButton = {keywordId:Int ->
            addNotificationViewModel.deleteKeyword(keywordId)
        }
        NotificationKeywordListAdapter(onClickDeleteKeywordButton)
    }

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
        binding.addNotificationFragment = this
        binding.viewModel = addNotificationViewModel


        binding.notificationKeywordListRV.also {
            val linearLayoutManager = LinearLayoutManager(parentActivity)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            it.layoutManager = linearLayoutManager
            it.setHasFixedSize(false)
            it.adapter = notificationKeywordListAdapter
        }

        addNotificationViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
        })

        addNotificationViewModel.notificationKeywords.observe(viewLifecycleOwner, Observer {
            it?.let {
                notificationKeywordListAdapter.submitList(it.toList())
                if (it.isNotEmpty()){
                    notificationKeywordListRV.smoothScrollToPosition(it.size-1)
                }
            }
        })

        addNotificationViewModel.deleteKeywordSuccess.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.boolean){
                    addNotificationViewModel.getNotificationKeywords()
                }
            }
        })
        return binding.root
    }

}