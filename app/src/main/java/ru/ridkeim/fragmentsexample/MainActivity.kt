package ru.ridkeim.fragmentsexample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() , ButtonsFragment.OnSelectedButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onButtonSelected(index : Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment2) as DataFragment?
        if(fragment == null || !fragment.isVisible){
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(DataFragment.BUTTON_INDEX, index)
            }
            intent.resolveActivity(packageManager)?.also {
                startActivity(intent)
            }
        }else{
            fragment.setText(index)
        }
    }
}