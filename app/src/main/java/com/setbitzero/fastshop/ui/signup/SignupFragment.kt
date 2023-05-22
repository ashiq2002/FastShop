package com.setbitzero.fastshop.ui.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.setbitzero.fastshop.databinding.FragmentSignupBinding
import com.setbitzero.fastshop.util.FormValidationUtil
import com.setbitzero.fastshop.util.InputTextType

class SignupFragment : Fragment() {
    private var _binding:FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInText.setOnClickListener {
            findNavController().popBackStack()
        }

        //validate email
        FormValidationUtil.validate(binding.emailContainer, binding.email, InputTextType.EMAIL)
        //validate password
        FormValidationUtil.validate(binding.passwordContainer, binding.password, InputTextType.PASSWORD)
    }

    //save user login state


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.wtf("DESTROY", "SignupFragment Destroyed")
    }

}