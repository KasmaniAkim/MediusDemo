package com.demo.medius

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.demo.medius.data.local.Resource
import com.demo.medius.data.model.OTPInput
import com.demo.medius.data.viewmodels.OTPViewModel
import com.demo.medius.utils.LottieProgressDialog
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val loginViewModel: OTPViewModel by viewModel()
    private lateinit var lottieProgressDialog: LottieProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestSMSPermission()
        val etOTP: EditText = findViewById(R.id.et_otpNumber)
        val btnLogin: TextView = findViewById(R.id.btn_login)
        SMSReceiver().setEditText(etOTP)
        lottieProgressDialog = LottieProgressDialog(this)
        setLoginObserver()
        etOTP.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (etOTP.text.length > 6) {
                    Toast.makeText(this@MainActivity, "6 digit limit", Toast.LENGTH_SHORT).show()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        btnLogin.setOnClickListener {
            loginUser(etOTP.text.toString())
        }
    }

    private fun requestSMSPermission() {
        val permission = Manifest.permission.RECEIVE_SMS
        val grant = ActivityCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permissionList = arrayOfNulls<String>(1)
            permissionList[0] = permission
            ActivityCompat.requestPermissions(this, permissionList, 1)
        }
    }

    private fun loginUser(text: String) {

        val login = OTPInput(
            text,
        )
        loginViewModel.checkOTP(login)
    }

    private fun setLoginObserver() {

        loginViewModel.performLoginObserver.observe(this@MainActivity) {
            if (it != null) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        lottieProgressDialog.showDialog()
                    }

                    Resource.Status.SUCCESS -> {
                        lottieProgressDialog.cancelDialog()
                        Toast.makeText(this@MainActivity, "OTP Success", Toast.LENGTH_SHORT).show()

                    }

                    Resource.Status.OFFLINE_ERROR -> {
                        lottieProgressDialog.cancelDialog()
                    }

                    Resource.Status.ERROR -> {
                        Toast.makeText(
                            this@MainActivity,
                            "" + it.error!!.message,
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Log.e("tag", " = = = = = " + it.error.message.toString())
                        lottieProgressDialog.cancelDialog()
                    }

                    Resource.Status.EMPTY -> {
                        lottieProgressDialog.cancelDialog()
                    }
                }
            }
        }
    }
}