package com.example.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.game.databinding.FragmentFourInRowBinding


class FourInRowFragment : Fragment(R.layout.fragment_four_in_row) {
    private val viewModel: FourInRowFragmentViewModel by viewModels()
    lateinit var binding: FragmentFourInRowBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFourInRowBinding.bind(view)
        binding.apply {
            lifecycleOwner = this@FourInRowFragment
            fourRowViewModel = viewModel
            val columnList = listOf(
                btn1Column1, btn2Column1, btn3Column1, btn4Column1, btn5Column1,
                btn1Column2, btn2Column2, btn3Column2, btn4Column2, btn5Column2,
                btn1Column3, btn2Column3, btn3Column3, btn4Column3, btn5Column3,
                btn1Column4, btn2Column4, btn3Column4, btn4Column4, btn5Column4,
                btn1Column5, btn2Column5, btn3Column5, btn4Column5, btn5Column5,
            )

            fun offClick() {
                columnList.forEach {
                    it.isClickable = false
                }
            }
            for (column in columnList) {
                column.setOnClickListener {
                    viewModel.chooseColumn(column.tag.toString().toInt())
                    if ("Win" in viewModel.player.value!!) {
                        offClick()
                    }
                }
            }
        }

    }
}