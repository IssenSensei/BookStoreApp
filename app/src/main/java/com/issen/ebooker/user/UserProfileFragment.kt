package com.issen.ebooker.user

import androidx.fragment.app.Fragment

class UserProfileFragment : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile)
//        initToolbar()
//
//        loadUserData()
//        profile_edit_button.setOnClickListener {
//            updateUser(
//                profile_email.text.toString(),
//                profile_name.text.toString(),
//                profile_surname.text.toString()
//            )
//        }
//
//
//        profile_password_change_button.setOnClickListener {
//            updatePassword(
//                profile_password.text.toString(),
//                profile_new_password.text.toString()
//            )
//        }
//    }
//
//
//    private fun loadUserData() {
//        val apiInterface = ApiInterface.create().getUser("getUser", ApiInterface.USER_ID)
//
//        apiInterface.enqueue(object : Callback<UserItem> {
//
//            override fun onResponse(
//                call: Call<UserItem>,
//                response: Response<UserItem>?
//            ) {
//                if (response?.body() != null) {
//                    Log.d("profile", response.body().toString())
//                    profile_email.setText(response.body()!!.email)
//                    profile_name.setText(response.body()!!.name)
//                    profile_surname.setText(response.body()!!.surname)
//                }
//            }
//
//            override fun onFailure(call: Call<UserItem>?, t: Throwable?) {
//            }
//        })
//    }
//
//    private fun updateUser(
//        name: String,
//        surname: String,
//        email: String
//    ) {
//        val apiInterface =
//            ApiInterface.create().updateUser("updateUser", ApiInterface.USER_ID, name, surname, email)
//
//        apiInterface.enqueue(object : Callback<Int> {
//
//            override fun onResponse(
//                call: Call<Int>,
//                response: Response<Int>?
//            ) {
//                if (response?.body() != null) {
//                    Log.d("profilerrrr", response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<Int>?, t: Throwable?) {
//            }
//        })
//    }
//
//    private fun updatePassword(password: String, newPassword: String) {
//        val apiInterface =
//            ApiInterface.create().changePassword("changePassword", password, newPassword, ApiInterface.USER_ID)
//
//        apiInterface.enqueue(object : Callback<Int> {
//
//            override fun onResponse(
//                call: Call<Int>,
//                response: Response<Int>?
//            ) {
//                if (response?.body() != null) {
//                    Log.d("profilerrrrpppp", response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<Int>?, t: Throwable?) {
//
//            }
//        })
//    }
//
//    private fun initToolbar() {
//        val toolbar: Toolbar = findViewById(R.id.profile_toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Powrót"
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            setResult(Activity.RESULT_CANCELED)
//            finish()
//        } else {
//            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}

