package com.yashrajsinh.gitprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashrajsinh.gitprofile.api.RetrofitClient
import com.yashrajsinh.gitprofile.model.MDetailsUserResponse
import com.yashrajsinh.gitprofile.model.MUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val user = MutableLiveData<MDetailsUserResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetails(username)
            .enqueue(object : Callback<MDetailsUserResponse> {
                override fun onResponse(
                    call: Call<MDetailsUserResponse>,
                    response: Response<MDetailsUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MDetailsUserResponse>, t: Throwable) {
                    Log.d("Failed", t.message + "")
                }

            })
    }

    fun getUserDetails(): LiveData<MDetailsUserResponse> {
        return user
    }
}