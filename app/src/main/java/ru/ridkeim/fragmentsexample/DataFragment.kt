package ru.ridkeim.fragmentsexample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DataFragment : Fragment() {
    private lateinit var textView: TextView
    private var selectedState = BUTTON_INDEX_DEFAULT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate $this bundle=$savedInstanceState")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_data, container, false)
        textView = rootView.findViewById(R.id.textView)
        val selectedState = savedInstanceState?.getInt(BUTTON_INDEX, BUTTON_INDEX_DEFAULT)
            ?: (arguments?.getInt(BUTTON_INDEX) ?: BUTTON_INDEX_DEFAULT)
        setText(selectedState)
        Log.d(TAG,"onCreateView selectedState=$selectedState $this")
        return rootView
    }

    fun setText(id : Int){
        selectedState = id
        if(selectedState != BUTTON_INDEX_DEFAULT){
            textView.text = resources.getString(R.string.button_pressed_message,id)
        } else{
            textView.text = resources.getString(R.string.text_view)
        }
        Log.d(TAG,"setText $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy $this")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BUTTON_INDEX,selectedState)
        Log.d(TAG,"onSaveInstanceState $this bundle=$outState")
    }

    companion object {
        const val BUTTON_INDEX = "BUTTON_INDEX"
        const val BUTTON_INDEX_DEFAULT = -1
        private val TAG = DataFragment::class.qualifiedName
    }

}