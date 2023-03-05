package com.example.myexperiments.fragments.list

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myexperiments.R
import com.example.myexperiments.model.User

class ListAdapter(val thisFragment: Fragment): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()


    class MyViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.costum_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.id_text).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_text).text = currentItem.firstName.toString()
        holder.itemView.findViewById<TextView>(R.id.lastName_text).text = currentItem.lastName.toString()
        holder.itemView.findViewById<TextView>(R.id.age_text).text = currentItem.age.toString()

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener {
            var bundle: Bundle = Bundle()
            bundle.putInt("id",currentItem.id)
            bundle.putString("firstName",currentItem.firstName.toString())
            bundle.putString("lastName", currentItem.lastName.toString())
            bundle.putString("age",currentItem.age.toString())
            findNavController(thisFragment).navigate(R.id.updateFragment, bundle)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}