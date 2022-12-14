package com.fintech15.loanadvisor

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
    private lateinit var binding: FragmentAdviceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdviceBinding.inflate(inflater, container, false)
        return binding.root
    }
}