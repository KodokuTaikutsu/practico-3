package com.example.practico3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3.R
import com.example.practico3.adapter.LikedPeopleAdapter
import com.example.practico3.viewmodel.PeopleViewModel

class LikesFragment : Fragment() {

    private lateinit var likesRecyclerView: RecyclerView
    private lateinit var backButton: Button
    private lateinit var likedPeopleAdapter: LikedPeopleAdapter
    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_likes, container, false)

        // Set up the RecyclerView with a GridLayoutManager for two columns
        likesRecyclerView = view.findViewById(R.id.likesRecyclerView)
        likesRecyclerView.layoutManager = GridLayoutManager(context, 2) // Set spanCount to 2 for two columns

        // Initialize the adapter with an empty list
        likedPeopleAdapter = LikedPeopleAdapter(emptyList())
        likesRecyclerView.adapter = likedPeopleAdapter

        // Observe the likedPeople list from the ViewModel
        viewModel.likedPeople.observe(viewLifecycleOwner) { likedPeople ->
            likedPeopleAdapter.updateData(likedPeople)
        }

        // Set up the back button to navigate back
        backButton = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}
