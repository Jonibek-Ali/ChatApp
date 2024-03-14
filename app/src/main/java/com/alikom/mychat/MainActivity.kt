package com.alikom.mychat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alikom.mychat.messenger.fragment.ChatDetailFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ChatDetailFragment())
            .commit()

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, ChatFragment())
//            .commit()
    }
}