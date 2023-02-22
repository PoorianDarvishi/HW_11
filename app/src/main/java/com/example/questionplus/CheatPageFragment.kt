package com.example.questionplus

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.questionplus.databinding.FragmentCheatPageBinding


class CheatPageFragment : Fragment(R.layout.fragment_cheat_page) {
    lateinit var binding : FragmentCheatPageBinding
    private val viewModel : QuizViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheatPageBinding.bind(view)
        binding.apply {
            quizViewModel = viewModel
            lifecycleOwner = this@CheatPageFragment
            buttonShowAnswer.setOnClickListener {
                viewModel.showAnswer()
            }
            buttonBack.setOnClickListener {
                viewModel.resetShowAnswer()
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<QuizPageFragment>(R.id.fragment_container_view)
                }
            }
        }
    }
}