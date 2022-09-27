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
import com.yashrajsinh.gitprofile.DetailUserViewModel
//Activity to show follwers
class FollowersFragment : Fragment(R.layout.fragment_followers) {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowersBinding.bind(view)
        //Fetching Adpater Data
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter

        }
        //show loading while loading the data
        showLoading(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }
        //make binding null if destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //Show Progress bar when fetching data
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}