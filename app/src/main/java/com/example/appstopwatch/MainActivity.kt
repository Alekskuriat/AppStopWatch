package com.example.appstopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.gb.stopwatch.view.FragmentWatch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()

        fragmentTransaction
            .add(R.id.watch_1, FragmentWatch())
            .add(R.id.watch_2, FragmentWatch())
            .commitNow()
    }

}