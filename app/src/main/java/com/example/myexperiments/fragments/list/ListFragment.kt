package com.example.myexperiments.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myexperiments.R
import com.example.myexperiments.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)

        //Recycler View
        val adapter = ListAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // attach the view model here

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer{user ->
            adapter.setData(user)
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionbuuton).setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }
        return view
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete_icon_image -> deleteAllUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_ ->

            mUserViewModel.deleteAllUser()
            Toast.makeText(context,"Data Deleted Successfully!",
                Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete All The Data?")
        builder.setMessage("Are you sure you want to delete all the data?")
        builder.create().show()
    }

}