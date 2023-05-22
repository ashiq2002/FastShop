package com.setbitzero.fastshop.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.setbitzero.fastshop.R
import com.setbitzero.fastshop.databinding.FragmentLoginBinding
import com.setbitzero.fastshop.util.FormValidationUtil
import com.setbitzero.fastshop.util.InputTextType
import com.setbitzero.fastshop.util.UserLoginStateUtil

class LoginFragment : Fragment() {
    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isEmailPasswordSave = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpText.setOnClickListener {
            findNavController().navigate(R.id.signupFragment)
        }

        //set saved data into EditTextView
        refillSavedData()

        //validate email
        FormValidationUtil.validate(binding.emailContainer, binding.email, InputTextType.EMAIL)
        //validate password
        FormValidationUtil.validate(binding.passwordContainer, binding.password, InputTextType.PASSWORD)

        //checked to save user login state
        binding.rememberMe.setOnCheckedChangeListener { _, isChecked ->
            isEmailPasswordSave = isChecked
        }

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            //save user email and password if isEmailPasswordSave is equal to true
            UserLoginStateUtil(requireContext())
                .saveUserEmailPassword(email = email, password = password, save = isEmailPasswordSave)
        }

    }

    //Refill user email password inputEditText from local database if database not empty
    private fun refillSavedData(){
        binding.email.setText(UserLoginStateUtil(requireContext()).getEmail())
        binding.password.setText(UserLoginStateUtil(requireContext()).getPassword())
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.wtf("DESTROY", "LoginFragment Destroyed")
    }

}