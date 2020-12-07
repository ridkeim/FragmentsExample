package ru.ridkeim.fragmentsexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ButtonsFragment : Fragment(), View.OnClickListener {

    interface OnSelectedButtonListener{
        fun onButtonSelected(index : Int)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_buttons, container, false)
        val button1 = rootView.findViewById<Button>(R.id.button1)
        val button2 = rootView.findViewById<Button>(R.id.button2)
        val button3 = rootView.findViewById<Button>(R.id.button3)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        return rootView
    }

    override fun onClick(v: View?) {
        val buttonIndex = translateIdToIndex(v!!.id)
        val onSelectedButtonListener = activity as OnSelectedButtonListener?
        onSelectedButtonListener?.onButtonSelected(buttonIndex)
    }

    private fun translateIdToIndex(id: Int) : Int{
        return when (id) {
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            else -> DataFragment.BUTTON_INDEX_DEFAULT
        }
    }

}