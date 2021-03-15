package com.anushka.navdemo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.anushka.navdemo1.databinding.ActivityMainBinding
import com.anushka.navdemo1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.button.setOnClickListener {
            val bundle:Bundle = bundleOf("user_input" to binding.editTextTextPersonName.text.toString())
            it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment,bundle)
        }
        return binding.root



    }

}