package com.example.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.FragmentFourInRowBinding


class FourInRowFragment : Fragment(R.layout.fragment_four_in_row), IClickListener {
    private val viewModel: FourInRowFragmentViewModel by viewModels()
    private lateinit var binding: FragmentFourInRowBinding
    lateinit var recyclerViewFragment: RecyclerView
    lateinit var adaptor: Adaptor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFourInRowBinding.bind(view)
        binding.apply {
            lifecycleOwner = this@FourInRowFragment
            fourRowViewModel = viewModel
            recyclerViewFragment = recyclerView
            adaptor = Adaptor(viewModel.getListPlace(), this@FourInRowFragment,viewModel.status)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
            recyclerView.adapter = adaptor
        }
    }

    override fun setOnClickListener(number: Int) {
        viewModel.choosePlace(number)
    }
}
