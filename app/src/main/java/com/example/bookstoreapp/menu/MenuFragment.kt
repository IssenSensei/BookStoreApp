package com.example.bookstoreapp.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookstoreapp.R
import com.example.bookstoreapp.auth.LoginActivity
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.user.UserProfileActivity
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profile_button.setOnClickListener {
            val intent = Intent(context, UserProfileActivity::class.java)
            context?.startActivity(intent)
        }

        app_info_button.setOnClickListener{
            val intent = Intent(context, AppInfoActivity::class.java)
            context?.startActivity(intent)
        }

        app_settings_button.setOnClickListener{
            val intent = Intent(context, AppSettingsActivity::class.java)
            context?.startActivity(intent)
        }

        logout_button.setOnClickListener{
            ApiInterface.USER_ID = -1
            val intent = Intent(context, LoginActivity::class.java)
            context?.startActivity(intent)
        }

        maps_button.setOnClickListener {

        }
    }
}