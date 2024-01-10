package com.example.alimentos_juliangarrido.UI.fragments.viewpager.tipo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alimentos_juliangarrido.R
import com.example.alimentos_juliangarrido.databinding.FragmentHomeBinding
import com.example.alimentos_juliangarrido.databinding.FragmentTiposBinding


class TiposFragment : Fragment() {

    private var _binding: FragmentTiposBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTiposBinding.inflate(inflater, container, false)



        return binding.root
    }


}