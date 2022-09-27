package com.yashrajsinh.gitprofile.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashrajsinh.gitprofile.api.RetrofitClient
import com.yashrajsinh.gitprofile.model.MUser
import com.yashrajsinh.gitprofile.model.MUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MMainView : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<MUser>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<MUserResponse> {
                override fun onResponse(
                    call: Call<MUserResponse>,
                    response: Response<MUserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<MUserResponse>, t: Throwable) {
                    Log.d("Failed ", t.message+"")
                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<MUser>> {
       return listUsers
    }
}