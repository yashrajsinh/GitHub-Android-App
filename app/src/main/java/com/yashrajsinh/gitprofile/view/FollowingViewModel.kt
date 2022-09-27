package com.yashrajsinh.gitprofile.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashrajsinh.gitprofile.api.RetrofitClient
import com.yashrajsinh.gitprofile.model.MUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/*
Fragemnt to show following
 */
class FollowingViewModel : ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<MUser>>()

    fun setListFollowing(username: String) {
        //Same as followers calling Retrofit to fetch json data
        RetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<MUser>> {
                override fun onResponse(
                    call: Call<ArrayList<MUser>>,
                    response: Response<ArrayList<MUser>>
                ) {
                    if (response.isSuccessful) {
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<MUser>>, t: Throwable) {
                    Log.d("Failed", t.message + "")
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<MUser>> {
        return  listFollowing
    }
}
