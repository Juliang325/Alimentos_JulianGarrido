package com.example.alimentos_juliangarrido.UI.fragments.viewpager

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alimentos_juliangarrido.R
import com.example.alimentos_juliangarrido.UI.adapter.alimento.AlimentoAdapter
import com.example.alimentos_juliangarrido.UI.adapter.alimentodetalle.DetalleAdapter
import com.example.alimentos_juliangarrido.UI.fragments.viewmodels.SharedViewModel
import com.example.alimentos_juliangarrido.databinding.FragmentTiposBinding
import com.example.alimentos_juliangarrido.modelo.daos.alimento.DaoAlimentoBDSimu
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.provider.AlimentoProvider
import com.google.android.material.bottomnavigation.BottomNavigationView


class TiposFragment : Fragment() {

    private var _binding: FragmentTiposBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var adapterAlimento: DetalleAdapter
    private lateinit var adapterReceta: DetalleAdapter
    private lateinit var adapterMenu: DetalleAdapter
    private lateinit var adapterDieta: DetalleAdapter
    private val llmanager:LinearLayoutManager =  LinearLayoutManager(context)
    private var tipoAli = "simple"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTiposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)


        // Observar cambios en la lista de alimentos y actualizar las vistas cuando cambie
        sharedViewModel.alimentoMutableList.observe(viewLifecycleOwner) { alimentos ->
            val alimentosSimples = alimentos.filter { it.tipo == "simple" }.toMutableList()
            val alimentosReceta = alimentos.filter { it.tipo == "receta" }
            val alimentosMenu = alimentos.filter { it.tipo == "menu" }
            val alimentosDietas = alimentos.filter { it.tipo == "dieta" }

            adapterAlimento = DetalleAdapter(
                alimentosSimples,
                onClickDelete = { position -> showDeleteConfirmationDialog(position) })
            adapterReceta = DetalleAdapter(
                alimentosReceta,
                onClickDelete = { position -> showDeleteConfirmationDialog(position) })
            adapterMenu = DetalleAdapter(
                alimentosMenu,
                onClickDelete = { position -> showDeleteConfirmationDialog(position) })
            adapterDieta = DetalleAdapter(
                alimentosDietas,
                onClickDelete = { position -> showDeleteConfirmationDialog(position) })

            setupRecyclerViews(tipoAli)
            initListener()

            // Obtener una referencia al BottomNavigationView
            val bottomNavigationView: BottomNavigationView =
                view.findViewById(R.id.bottom_navigation)

            // Configurar un listener para manejar la selección de elementos y mostrar el recyclerview
            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_alimento -> {
                        tipoAli = "simple"
                        setupRecyclerViews(tipoAli)
                    }
                    R.id.menu_receta -> {
                        tipoAli = "receta"
                        setupRecyclerViews(tipoAli)
                    }
                    R.id.menu_menu -> {
                        tipoAli = "menu"
                        setupRecyclerViews(tipoAli)
                    }
                    R.id.menu_dieta -> {
                        tipoAli = "dieta"
                        setupRecyclerViews(tipoAli)
                    }
                }
                true
            }
        }
    }

    /***** CONFIGURACION DEL RECYCLERVIEW *****/
    private fun setupRecyclerViews(tipo:String) {
        when(tipo){
            "simple" -> {
                binding.rvTipo.layoutManager = llmanager
                binding.rvTipo.adapter = adapterAlimento
            }
            "receta" -> {
                binding.rvTipo.layoutManager = llmanager
                binding.rvTipo.adapter = adapterReceta
            }
            "menu" -> {
                binding.rvTipo.layoutManager = llmanager
                binding.rvTipo.adapter = adapterMenu
            }
            "dieta" -> {
                binding.rvTipo.layoutManager = llmanager
                binding.rvTipo.adapter = adapterDieta
            }
        }

    }
    /********  BORRAR ALIMENTO   **********/
    //Mostrar alertDialogo para confirmar el borrado de alimento
    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmación")
        builder.setMessage("¿Estás seguro de que quieres borrarlo?")

        builder.setPositiveButton("Sí") { _, _ ->
            onClickDelete(position)
        }

        builder.setNegativeButton("No") { _, _ ->
        }

        val dialog = builder.create()
        dialog.show()
    }
    //Logica para borrar elemento de la lista
    private fun onClickDelete(position: Int) {
        if (position >= 0 && position < (sharedViewModel.alimentoMutableList.value?.size ?: 0)) {
            val nuevaLista = sharedViewModel.alimentoMutableList.value?.toMutableList()
            nuevaLista?.removeAt(position)
            sharedViewModel.alimentoMutableList.postValue(nuevaLista)
        }
    }
    /****** AÑADIR ELEMENTO *****/
    private fun initListener(){
        binding.btnAdd.setOnClickListener { showDialog() }
    }
    private fun showDialog() {
        val dialog = context?.let { Dialog(it) }
        dialog!!.setContentView(R.layout.add_dialog)

        val etNombre: EditText = dialog.findViewById(R.id.etDialog)
        val etGrHC: EditText = dialog.findViewById(R.id.etGrHC)
        val etGrLip: EditText = dialog.findViewById(R.id.etGrLip)
        val etGrPro: EditText = dialog.findViewById(R.id.etGrPro)
        val btnDialog: Button = dialog.findViewById(R.id.btnAddAlimento)

        btnDialog.setOnClickListener {
            if (tipoAli == "simple") {
                val nombre = etNombre.text.toString()
                val grHC = etGrHC.text.toString().toDoubleOrNull() ?: 0.0
                val grLip = etGrLip.text.toString().toDoubleOrNull() ?: 0.0
                val grPro = etGrPro.text.toString().toDoubleOrNull() ?: 0.0

                //Almacenamos los datos para crear un nuevo alimento
                updateAlimentos(Alimento(nombre, tipoAli, grHC, grLip, grPro))
            }else{
                Toast.makeText(context,"En próximas versiones...", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        dialog.show()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun updateAlimentos(a: Alimento) {
        try {
            val alimentosList = sharedViewModel.alimentoMutableList.value?.toMutableList()

            if (alimentosList != null) {
                alimentosList.add(1, a)
                sharedViewModel.daoAlimentos.addAlimento(a)
                sharedViewModel.alimentoMutableList.postValue(alimentosList)
                binding.rvTipo.adapter?.notifyItemInserted(1)
                llmanager.scrollToPositionWithOffset(1, 20)
            } else {
                Log.e("TiposFragment", "Error: La lista de alimentos es nula")
            }
        } catch (e: Exception) {
            Log.e("TiposFragment", "Error al actualizar alimentos: ${e.message}")
        }
    }

}