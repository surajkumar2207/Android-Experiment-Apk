package com.example.myexperiments.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myexperiments.R
import com.example.myexperiments.model.User
import com.example.myexperiments.viewmodel.UserViewModel
import com.example.myexperiments.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentAddBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]



        binding.addButton.setOnClickListener {
            insertDataToDatabase(binding)
        }


        return  binding.root
    }


    private fun checkForEmpty(firstName: String, secondName: String, age: String): Boolean {

        return ( TextUtils.isEmpty(firstName) || TextUtils.isEmpty(secondName) || TextUtils.isEmpty(age))

    }

    private fun insertDataToDatabase(binding: FragmentAddBinding) {
        val firstName = binding.firstNameEdittext.text.toString()
        val secondName = binding.secondNameEdittext.text.toString()
        val age = binding.ageEditText.text.toString()

        if(!checkForEmpty(firstName, secondName, age)){

            val user = User(0, firstName, secondName, Integer.parseInt(age))
            mUserViewModel.addUser(user)
            Toast.makeText(context, "Entered Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        }
        else {
            Toast.makeText(context, "Please Do Not Leave Empty Text", Toast.LENGTH_SHORT).show()
        }

    }


}