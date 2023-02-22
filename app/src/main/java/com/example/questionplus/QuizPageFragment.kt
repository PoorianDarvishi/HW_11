package com.example.questionplus

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.*
import com.example.questionplus.databinding.FragmentQuizPageBinding

class QuizPageFragment : Fragment(R.layout.fragment_quiz_page) {
    lateinit var binding: FragmentQuizPageBinding
    private val viewModel: QuizViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuizPageBinding.bind(view)
        binding.apply {
            quizViewModel = viewModel
            lifecycleOwner = this@QuizPageFragment
            buttonNext.setOnClickListener {
                viewModel.nextQuestion()
            }

            buttonPrev.setOnClickListener {
                viewModel.prevQuestion()
            }

            buttonTrue.setOnClickListener {
                showToast(viewModel.checkAnswer(QuizViewModel.TRUE))
            }

            buttonFalse.setOnClickListener {
                showToast(viewModel.checkAnswer(QuizViewModel.FALSE))
            }

            buttonCheat.setOnClickListener {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<CheatPageFragment>(R.id.fragment_container_view)
                }
            }
        }
    }

    private fun showToast(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}