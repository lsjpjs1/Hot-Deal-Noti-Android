package com.hotdealnoti.login.view

import android.content.ContentValues.TAG
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
import androidx.navigation.fragment.findNavController
import com.hotdealnoti.R
import com.hotdealnoti.data.local.App
import com.hotdealnoti.databinding.FragmentLoginBinding
import com.hotdealnoti.databinding.FragmentNotificationBinding
import com.hotdealnoti.login.viewmodel.LoginViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import kotlin.math.log


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mContext: Context
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (App.prefs.getValue("AUTH_TOKEN")!="") {
            if (findNavController().currentDestination?.id==R.id.loginFragment){
                findNavController().navigate(R.id.action_loginFragment_to_notificationFragment)

            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginFragment = this

        loginViewModel.loginSuccess.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("succ", it.toString())
                if (it) findNavController().navigate(R.id.action_loginFragment_to_notificationFragment)
            }
        })

        loginViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(mContext,it,Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
//                Toast.makeText(requireContext(),"1"+error.message,Toast.LENGTH_LONG).show()
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                //우리 서버에서 인증 토큰 가져오는 로직
                loginViewModel.kakaoLogin(token.accessToken)
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
//                    Toast.makeText(requireContext(),"2"+error.message,Toast.LENGTH_LONG).show()
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        requireContext(),
                        callback = callback
                    )
                } else if (token != null) {

                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    //우리 서버에서 인증 토큰 가져오는 로직
                    loginViewModel.kakaoLogin(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }

    }


}