package com.example.alimentos_juliangarrido.UI.fragments.viewpager.tipo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alimentos_juliangarrido.R
import com.example.alimentos_juliangarrido.UI.adapter.alimento.AlimentoAdapter
import com.example.alimentos_juliangarrido.UI.fragments.viewmodels.SharedViewModel
import com.example.alimentos_juliangarrido.databinding.FragmentHomeBinding
import com.example.alimentos_juliangarrido.databinding.FragmentTiposBinding
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.provider.AlimentoProvider
import com.google.android.material.bottomnavigation.BottomNavigationView


class TiposFragment : Fragment() {

    private var _binding: FragmentTiposBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val librosMutableList:MutableList<Alimento> = AlimentoProvider.alimentos.toMutableList()
    private lateinit var adapterAlimento: AlimentoAdapter
    private lateinit var adapterReceta: AlimentoAdapter
    private lateinit var adapterMenu: AlimentoAdapter
    private lateinit var adapterDieta: AlimentoAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTiposBinding.inflate(inflater, container, false)
        //Separamos los alimentos por su tipo
        val alimentosSimples = AlimentoProvider.alimentos.filter { it.tipo == "simple" }
        val alimentosReceta= AlimentoProvider.alimentos.filter { it.tipo == "receta" }
        val alimentosMenu = AlimentoProvider.alimentos.filter { it.tipo == "menu" }
        val alimentosDietas = AlimentoProvider.alimentos.filter { it.tipo == "dieta" }

        //Creamos los distintos adapter
        adapterAlimento = AlimentoAdapter(alimentosSimples)
        adapterReceta = AlimentoAdapter(alimentosReceta)
        adapterMenu = AlimentoAdapter(alimentosMenu)
        adapterDieta = AlimentoAdapter(alimentosDietas)

        setupRecyclerViews("simple")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener una referencia al BottomNavigationView
        val bottomNavigationView: BottomNavigationView = view.findViewById(R.id.bottom_navigation)

        // Configurar un listener para manejar la selección de elementos y mostrar el recyclerview
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_alimento -> {
                    setupRecyclerViews("simple")
                }
                R.id.menu_receta -> {
                    setupRecyclerViews("receta")
                }
                R.id.menu_menu -> {
                    setupRecyclerViews("menu")
                }
                R.id.menu_dieta -> {
                    setupRecyclerViews("dieta")
                }
            }
            true
        }
    }

    private fun setupRecyclerViews(tipo:String) {
        when(tipo){
            "simple" -> {
                binding.rvTipo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTipo.adapter = adapterAlimento
            }
            "receta" -> {
                binding.rvTipo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTipo.adapter = adapterReceta
            }
            "menu" -> {
                binding.rvTipo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTipo.adapter = adapterMenu
            }
            "dieta" -> {
                binding.rvTipo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTipo.adapter = adapterDieta
            }
        }

    }


}