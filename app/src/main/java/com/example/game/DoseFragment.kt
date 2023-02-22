package com.example.game

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.game.databinding.FragmentDoseBinding

class DoseFragment : Fragment(R.layout.fragment_dose) {
     private val viewModel: DoseViewModel by viewModels()
    lateinit var binding: FragmentDoseBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDoseBinding.bind(view)
        binding.apply {
            doseViewModel = viewModel
            lifecycleOwner = this@DoseFragment
            val buttonList = listOf(button1,button2,button3,button4,button5,button6,button7,button8,button9)
            fun offClickable(button: Button){
                button.isClickable = false
            }
            fun offClickAll(){
                buttonList.forEach {
                    it.isClickable = false
                }
            }
            fun onClickAll(){
                buttonList.forEach {
                    it.isClickable = true
                }
            }
            for (button in buttonList){
                val tagPlace = button.tag.toString().toInt()
                button.setOnClickListener {
                    offClickable(button)
                    if(viewModel.choose(tagPlace) != StatusGame.NONE) offClickAll()
                }
                if (viewModel.checkChoose(tagPlace)){
                    offClickable(button)
                }
            }
            buttonReset.setOnClickListener {
                viewModel.reset()
                onClickAll()
            }
        }

    }
}