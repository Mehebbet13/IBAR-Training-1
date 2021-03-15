package com.anushka.navdemo5


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.anushka.navdemo5.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.termsButton.setOnClickListener {
//            var bundle = bundleOf("terms" to "terms")
            it.findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
        }
        binding.signUpButton.setOnClickListener {
//            var bundle = bundleOf("terms" to "terms")
            it.findNavController().navigate(R.id.action_homeFragment_to_nameFragment)
        }
        return  binding.root
    }
}
