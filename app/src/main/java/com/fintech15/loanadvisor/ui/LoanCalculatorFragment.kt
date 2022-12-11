package com.fintech15.loanadvisor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fintech15.loanadvisor.databinding.FragmentLoanCalculatorBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class LoanCalculatorFragment : Fragment() {

    private var _binding: FragmentLoanCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoanCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.getAdviceButton.setOnClickListener {
            val action =
                LoanCalculatorFragmentDirections.actionLoanCalculatorFragmentToAdviceFragment()
            view.findNavController().navigate(action)
        }
    }
}