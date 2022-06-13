package com.pak.mvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.pak.mvvm.R
import com.pak.mvvm.databinding.FragmentLoginBinding
import com.pak.mvvm.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {

    lateinit var binding : FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        val root = binding.root
        return root

    }


}