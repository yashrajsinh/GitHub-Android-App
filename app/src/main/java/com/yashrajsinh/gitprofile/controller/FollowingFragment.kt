package com.yashrajsinh.gitprofile.controller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yashrajsinh.gitprofile.R
import com.yashrajsinh.gitprofile.adapter.UserAdapter
import com.yashrajsinh.gitprofile.databinding.FragmentFollowersBinding
import com.yashrajsinh.gitprofile.view.FollowersViewModel
import com.yashrajsinh.gitprofile.view.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_followers) {
    //Activity to show follwing same as folowers
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowersBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter

        }
        showLoading(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}