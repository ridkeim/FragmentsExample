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
        isTwoPane =  findViewById<View>(R.id.fragment2) != null
        if(isTwoPane){
            val dataFragment = supportFragmentManager.findFragmentByTag(dataFragmentTagLandscape) as DataFragment? ?: DataFragment()
            val isNewFragment = dataFragment.arguments == null
            if(isNewFragment) {
                dataFragment.arguments = Bundle()
            }
            dataFragment.arguments?.apply {
                putInt(DataFragment.BUTTON_INDEX, selectedButton)
            }
            if(isNewFragment){
                supportFragmentManager.commit {
                    add(R.id.fragment2, dataFragment, dataFragmentTagLandscape)
                }
            }
        }else{
            val dataFragment = supportFragmentManager.findFragmentByTag(dataFragmentTagPortrait) as DataFragment?
            dataFragment?.arguments?.apply {
                putInt(DataFragment.BUTTON_INDEX, selectedButton)
            }
        }
        Log.d(TAG,"onCreate isTwoPane=$isTwoPane")
    }

    override fun onButtonSelected(index : Int) {
        val dF = supportFragmentManager.findFragmentByTag(dataFragmentTagPortrait) as DataFragment?
        selectedButton = index
        if(isTwoPane){
            val dataFragment =
                supportFragmentManager.findFragmentByTag(dataFragmentTagLandscape) as DataFragment?
            dataFragment?.updateText(index)
        }else{
            val dataFragment = supportFragmentManager.findFragmentByTag(dataFragmentTagPortrait) as DataFragment? ?: DataFragment()
            if (dataFragment.arguments == null){
                dataFragment.arguments = Bundle()
            }
            dataFragment.arguments?.apply {
                    putInt(DataFragment.BUTTON_INDEX, index)
            }
            supportFragmentManager.commit {
                replace(R.id.container,dataFragment,dataFragmentTagPortrait)
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
        private const val dataFragmentTagPortrait = "dataFragmentTagPortrait"
        private const val dataFragmentTagLandscape = "dataFragmentTagLandscape"
        private const val buttonsFragmentTag = "buttonsFragmentTag"
        private val TAG = MainActivity::class.qualifiedName
        private const val SELECTED_BUTTON = "selected_button"
        private const val SELECTED_BUTTON_DEFAULT = -1

    }
}