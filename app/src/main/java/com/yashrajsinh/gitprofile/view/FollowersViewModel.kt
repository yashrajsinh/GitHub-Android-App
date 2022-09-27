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
Fragemnt to show followers
 */
class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<MUser>>()

    fun setListFollowers(username: String) {
        //Calling retro fit to get json data
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<MUser>> {
                override fun onResponse(
                    call: Call<ArrayList<MUser>>,
                    response: Response<ArrayList<MUser>>
                ) {
                    //if response is success
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<MUser>>, t: Throwable) {
                    Log.d("Failed", t.message + "")
                }

            })
    }

    fun getListFollowers(): LiveData<ArrayList<MUser>> {
        return  listFollowers
    }
}
