package com.yashrajsinh.gitprofile.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yashrajsinh.gitprofile.DetailUserViewModel
import com.yashrajsinh.gitprofile.databinding.ActivityDetailUserBinding
import com.yashrajsinh.gitprofile.databinding.ActivityMainBinding
//Activity to show User details
class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
    //vars for binding
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Getting user name from another activity
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)
        if (username != null) {
            viewModel.setUserDetail(username)
        }
            //Model to show data on UI widgets
        viewModel.getUserDetails().observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers}Followers"
                    tvFollowing.text = "${it.following}Following"
                    tvBio.text = it.bio
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(imgProfile)
                }
            }
        }
        //Pager to show users deatils
        val sectionPagerAdpater = SectionPagerAdapter(this, supportFragmentManager,bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdpater
            tabs.setupWithViewPager(viewPager)
        }
    }
}