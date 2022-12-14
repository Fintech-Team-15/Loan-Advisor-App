package com.fintech15.loanadvisor

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
    private lateinit var binding: FragmentCompareLoansBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCompareLoansBinding.inflate(inflater, container, false)
        return binding.root
    }
}