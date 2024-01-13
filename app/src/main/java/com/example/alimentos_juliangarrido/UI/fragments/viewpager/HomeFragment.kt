package com.example.alimentos_juliangarrido.UI.fragments.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alimentos_juliangarrido.UI.adapter.alimento.AlimentoAdapter
import com.example.alimentos_juliangarrido.databinding.FragmentHomeBinding
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.provider.AlimentoProvider


class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val librosMutableList:MutableList<Alimento> = AlimentoProvider.alimentos.toMutableList()
    private lateinit var adapterAlimento: AlimentoAdapter
    private lateinit var adapterReceta: AlimentoAdapter
    private lateinit var adapterMenu: AlimentoAdapter
    private lateinit var adapterDieta: AlimentoAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alimentosSimples = AlimentoProvider.alimentos.filter { it.tipo == "simple" }
        val alimentosReceta= AlimentoProvider.alimentos.filter { it.tipo == "receta" }
        val alimentosMenu = AlimentoProvider.alimentos.filter { it.tipo == "menu" }
        val alimentosDietas = AlimentoProvider.alimentos.filter { it.tipo == "dieta" }

        adapterAlimento = AlimentoAdapter(alimentosSimples)
        adapterReceta = AlimentoAdapter(alimentosReceta)
        adapterMenu = AlimentoAdapter(alimentosMenu)
        adapterDieta = AlimentoAdapter(alimentosDietas)



        binding.rvAlimento.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvAlimento.adapter = adapterAlimento

        binding.rvReceta.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvReceta.adapter = adapterReceta

        binding.rvMenu.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMenu.adapter = adapterMenu

        binding.rvDieta.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDieta.adapter = adapterDieta
    }





}