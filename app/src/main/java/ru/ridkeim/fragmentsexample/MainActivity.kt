package ru.ridkeim.fragmentsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() , ButtonsFragment.OnSelectedButtonListener {

    private var isTwoPane = false

    private var selectedButton = SELECTED_BUTTON_DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectedButton = savedInstanceState?.getInt(SELECTED_BUTTON) ?: SELECTED_BUTTON_DEFAULT
        isTwoPane = findViewById<View>(R.id.fragment2) != null
        Log.d(TAG,"onCreate isTwoPane=$isTwoPane")
    }

    override fun onButtonSelected(index : Int) {
        val dF = supportFragmentManager.findFragmentByTag(dataFragmentTag) as DataFragment?
        selectedButton = index
        if(isTwoPane){
            val dataFragment =
                supportFragmentManager.findFragmentById(R.id.fragment2) as DataFragment?
            dataFragment?.setText(index)
        }else{
            val dataFragment = DataFragment()
            dataFragment.arguments = Bundle().apply {
                putInt(DataFragment.BUTTON_INDEX, index)
            }
            supportFragmentManager.commit {
                replace(R.id.container,dataFragment,dataFragmentTag)
                addToBackStack(null)
            }
        }
        Log.d(TAG,"isTwoPane=$isTwoPane dataFragmentByTag=$dF")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_BUTTON,selectedButton)
    }
    companion object {
        private const val dataFragmentTag = "dataFragmentTag"
        private const val buttonsFragmentTag = "buttonsFragmentTag"
        private val TAG = MainActivity::class.qualifiedName
        private const val SELECTED_BUTTON = "selected_button"
        private const val SELECTED_BUTTON_DEFAULT = -1

    }
}