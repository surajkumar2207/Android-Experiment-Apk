package com.example.myexperiments.fragments.update

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiments.R
import com.example.myexperiments.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

   @SuppressLint("MissingInflatedId")
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_fragmenrt, container, false)


        setHasOptionsMenu(true)

        view.findViewById<TextView>(R.id.firstNameEdittext).text = arguments?.getString("firstName").toString()
        view.findViewById<TextView>(R.id.secondNameEdittext).text = arguments?.getString("lastName").toString()
        view.findViewById<TextView>(R.id.ageEditText).text = arguments?.getString("age").toString()



       mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

       view.findViewById<Button>(R.id.updateItButton).setOnClickListener {
          mUserViewModel.updateUser(view.findViewById<TextView>(R.id.firstNameEdittext).text.toString(),
              view.findViewById<TextView>(R.id.secondNameEdittext).text.toString(),
              view.findViewById<TextView>(R.id.ageEditText).text.toString().toInt() ,
              arguments?.getInt("id").toString().toInt()
          )
           Toast.makeText(context,"Data Saved Successfully!",Toast.LENGTH_SHORT).show()
           activity?.onBackPressed()
       }

       return view
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete_icon_image -> deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_ ->

            mUserViewModel.deleteThisUser(arguments?.getInt("id").toString().toInt())
            Toast.makeText(context,"${view?.findViewById<TextView>(R.id.firstNameEdittext)?.text.toString()} Deleted Successfully!",Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${view?.findViewById<TextView>(R.id.firstNameEdittext)?.text.toString()}")
        builder.setMessage("Are you sure you want to delete?")
        builder.create().show()
    }


}