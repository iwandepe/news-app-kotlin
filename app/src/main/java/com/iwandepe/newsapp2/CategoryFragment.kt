package com.iwandepe.newsapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.iwandepe.newsapp2.databinding.FragmentCategoryBinding
import com.iwandepe.newsapp2.databinding.FragmentWebViewBinding

class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCategoryBinding> (inflater, R.layout.fragment_category, container, false)
        binding.apply {
            businessCategory.setOnClickListener {v: View ->
                val action = CategoryFragmentDirections.actionCategoryFragmentToNewsFragment("business")
                v.findNavController().navigate(action)
            }
            entertainmentCategory.setOnClickListener {v: View ->
                val action = CategoryFragmentDirections.actionCategoryFragmentToNewsFragment("entertainment")
                v.findNavController().navigate(action)
            }
            healthCategory.setOnClickListener{v: View ->
                val action = CategoryFragmentDirections.actionCategoryFragmentToNewsFragment("health")
                v.findNavController().navigate(action)
            }
            technologyCategory.setOnClickListener {v: View ->
                val action = CategoryFragmentDirections.actionCategoryFragmentToNewsFragment("technology")
                v.findNavController().navigate(action)
            }
        }
        return binding.root
    }

}