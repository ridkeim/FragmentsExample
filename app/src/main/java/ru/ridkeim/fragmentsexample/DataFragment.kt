package ru.ridkeim.fragmentsexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DataFragment : Fragment() {
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_data, container, false)
        textView = rootView.findViewById(R.id.textView)
        return rootView
    }

    fun setText(id : Int){
        if(id != BUTTON_INDEX_DEFAULT){
            textView.text = resources.getString(R.string.button_pressed_message,id)
        } else{
            textView.text = resources.getString(R.string.text_view)
        }
    }

    companion object {
        const val BUTTON_INDEX = "BUTTON_INDEX"
        const val BUTTON_INDEX_DEFAULT = -1
    }

}