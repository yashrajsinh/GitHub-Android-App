package com.yashrajsinh.gitprofile.controller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yashrajsinh.gitprofile.R
import com.yashrajsinh.gitprofile.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment(R.layout.fragment_followers) {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowersBinding.bind(view)
    }
}