package com.capstone.tesfirebase.ui.main.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.tesfirebase.databinding.FragmentHistoryBinding
import com.capstone.tesfirebase.ui.ViewModelFactory

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyAdapter: HistoryAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        historyViewModel = obtainViewModel()

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get data from viewmodel
        historyViewModel.getHistory().observe(requireActivity()) {
            if (it != null) {
                // Create the adapter
                historyAdapter = HistoryAdapter(it)
                binding.rvStorymain.adapter = historyAdapter
            }
        }
        // Set up RecyclerView
        binding.rvStorymain.layoutManager = LinearLayoutManager(requireContext())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel(): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(requireActivity(), factory)[HistoryViewModel::class.java]
    }
}