package com.fintech15.loanadvisor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fintech15.loanadvisor.databinding.FragmentCompareLoansBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class CompareLoansFragment : Fragment() {
    private var _binding: FragmentCompareLoansBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompareLoansBinding.inflate(inflater, container, false)
        return binding.root
    }
}