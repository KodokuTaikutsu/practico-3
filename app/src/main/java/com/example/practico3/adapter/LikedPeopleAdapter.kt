package com.example.practico3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practico3.R
import com.example.practico3.model.Person // Assuming the Person class is in the model package
import de.hdodenhof.circleimageview.CircleImageView

class LikedPeopleAdapter(private var likedPeople: List<Person>) :
    RecyclerView.Adapter<LikedPeopleAdapter.LikedPeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedPeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_liked_person, parent, false)
        return LikedPeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikedPeopleViewHolder, position: Int) {
        val person = likedPeople[position]
        holder.personName.text = person.name
        // Load the first photo in the person's photos list
        Glide.with(holder.personImage.context)
            .load(person.photos[0]) // Assuming photos is a List<Int> of drawable resource IDs
            .into(holder.personImage)
    }

    override fun getItemCount(): Int = likedPeople.size

    inner class LikedPeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personImage: CircleImageView = view.findViewById(R.id.personImage)
        val personName: TextView = view.findViewById(R.id.personName)
    }

    // Method to update the data and notify the adapter of changes
    fun updateData(newLikedPeople: List<Person>) {
        likedPeople = newLikedPeople.toList() // Creates a new list reference
        notifyDataSetChanged()
    }
}
