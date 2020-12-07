package ru.ridkeim.fragmentsexample

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
            return
        }
        val buttonIndex =
            intent.getIntExtra(DataFragment.BUTTON_INDEX, DataFragment.BUTTON_INDEX_DEFAULT)
        if(buttonIndex != DataFragment.BUTTON_INDEX_DEFAULT){
            val dataFragment =
                supportFragmentManager.findFragmentById(R.id.fragment2) as DataFragment?
            dataFragment?.setText(buttonIndex)
        }
    }
}