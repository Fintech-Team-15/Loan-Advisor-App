package com.fintech15.loanadvisor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fintech15.loanadvisor.databinding.FragmentAdviceBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class AdviceFragment : Fragment() {
    private var _binding: FragmentAdviceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAdviceBinding.inflate(inflater, container, false)
        return binding.root
    }
}