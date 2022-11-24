package com.fintech15.loanadvisor

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

    private lateinit var binding: FragmentLoanCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoanCalculatorBinding.inflate(inflater, container, false)
        binding.getAdviceButton.setOnClickListener {
            val action =
                LoanCalculatorFragmentDirections.actionLoanCalculatorFragmentToAdviceFragment()
            view?.findNavController()?.navigate(action)
        }
        return binding.root
    }
}