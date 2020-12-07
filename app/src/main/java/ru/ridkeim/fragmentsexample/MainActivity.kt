package ru.ridkeim.fragmentsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() , ButtonsFragment.OnSelectedButtonListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onButtonSelected(index : Int) {
        val dataFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as DataFragment?
        dataFragment?.setText(index)
    }
}