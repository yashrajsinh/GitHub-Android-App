package com.yashrajsinh.gitprofile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yashrajsinh.gitprofile.controller.MMainView
import com.yashrajsinh.gitprofile.R
import com.yashrajsinh.gitprofile.adapter.UserAdapter
import com.yashrajsinh.gitprofile.controller.DetailUserActivity
import com.yashrajsinh.gitprofile.databinding.ActivityMainBinding
import com.yashrajsinh.gitprofile.model.MUser

class MainActivity : AppCompatActivity() {
    //Binding view
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MMainView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //binding to the root view
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        //On Click for search
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MUser) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MMainView::class.java)
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
            btnSearch.setOnClickListener {
                searchUser()
            }
            //Search query
            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    //Command
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        //Getting the search user
        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }
    //Fun to search user
    private fun searchUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }
    //Progress bar to show loading
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}