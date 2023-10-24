package com.demo.medius.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.medius.data.local.Resource
import com.demo.medius.data.model.OTPData
import com.demo.medius.data.model.OTPInput
import com.demo.medius.data.retrofit.OTPRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class OTPViewModel(private val loginRepository: OTPRepository) : ViewModel() {

    //registerUser
    private val performLogin = MutableLiveData<Resource<OTPData>>()
    val performLoginObserver: LiveData<Resource<OTPData>>
        get() = performLogin

    fun checkOTP(login: OTPInput) {
        viewModelScope.launch {
            try {
                performLogin.value = Resource.loading()
                val response = loginRepository.checkOTP(login)
                if (response.data != null) {
                    response.data.let { data ->
                        data.id = response.data.id
                        data.name = response.data.name
                        data.password = response.data.password
                        data.phone = response.data.phone
                        data.empId = response.data.empId
                        data.createdAt = response.data.createdAt
                        data.updatedAt = response.data.updatedAt
                        data.deletedAt = response.data.deletedAt
                        data.isAdmin = response.data.isAdmin
                        performLogin.value = Resource.success(data)
                    }
                } else {

                    performLogin.value = Resource.error(Exception(response.msg))
                }
            } catch (e: Exception) {
                Log.e("tag", " = = loginUser = =  " + e.message)
                if (e is UnknownHostException) {
                    performLogin.value = Resource.offlineError()
                } else {
                    performLogin.value = Resource.error(e)
                }
            }
        }
    }
}