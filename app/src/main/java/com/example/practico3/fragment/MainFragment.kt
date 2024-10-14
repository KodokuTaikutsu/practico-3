package com.example.practico3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.practico3.R
import com.example.practico3.adapter.PhotoAdapter
import com.example.practico3.databinding.FragmentMainBinding
import com.example.practico3.viewmodel.PeopleViewModel

class MainFragment : Fragment() {

    private val viewModel: PeopleViewModel by activityViewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var indicators: Array<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.peopleList.observe(viewLifecycleOwner) { people ->
            if (people.isNotEmpty()) {
                val person = people[0]
                binding.personName.text = person.name
                val adapter = PhotoAdapter(person.photos)
                binding.viewPager.adapter = adapter

                setupIndicatorDots(person.photos.size)
                updateIndicator(0)  // Set the first dot as active initially

                binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                    override fun onPageSelected(position: Int) {
                        updateIndicator(position)
                    }
                    override fun onPageScrollStateChanged(state: Int) {}
                })

                binding.likeButton.setOnClickListener {
                    viewModel.likePerson(person)
                }

                binding.dislikeButton.setOnClickListener {
                    viewModel.dislikePerson(person)
                }
            } else {
                binding.personName.text = "No more people"
                binding.likeButton.visibility = View.GONE
                binding.dislikeButton.visibility = View.GONE
            }
        }

        binding.likesButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_likesFragment)
        }
    }

    private fun setupIndicatorDots(count: Int) {
        indicators = Array(count) { ImageView(context) }
        binding.carouselIndicator.removeAllViews()

        for (i in indicators.indices) {
            indicators[i] = ImageView(context).apply {
                setImageResource(R.drawable.indicator_inactive) // Set inactive dot drawable
                val params = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart = 8
                    marginEnd = 8
                }
                binding.carouselIndicator.addView(this, params)
            }
        }
    }

    private fun updateIndicator(position: Int) {
        for (i in indicators.indices) {
            indicators[i].setImageResource(
                if (i == position) R.drawable.indicator_active else R.drawable.indicator_inactive
            )
        }
    }
}
