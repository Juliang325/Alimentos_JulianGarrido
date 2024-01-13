package com.example.alimentos_juliangarrido.UI.fragments.viewpager

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alimentos_juliangarrido.R
import com.example.alimentos_juliangarrido.UI.adapter.alimentodetalle.DetalleAdapter
import com.example.alimentos_juliangarrido.UI.fragments.viewmodels.SharedViewModel
import com.example.alimentos_juliangarrido.databinding.FragmentTiposBinding
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.provider.AlimentoProvider
import com.google.android.material.bottomnavigation.BottomNavigationView


class TiposFragment : Fragment() {

    private var _binding: FragmentTiposBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val librosMutableList:MutableList<Alimento> = AlimentoProvider.alimentos.toMutableList()
    private lateinit var adapterAlimento: DetalleAdapter
    private lateinit var adapterReceta: DetalleAdapter
    private lateinit var adapterMenu: DetalleAdapter
    private lateinit var adapterDieta: DetalleAdapter
    private lateinit var alimentosSimples : MutableList<Alimento>
    private val llmanager:LinearLayoutManager =  LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTiposBinding.inflate(inflater, container, false)
        //Separamos los alimentos por su tipo
        alimentosSimples = AlimentoProvider.alimentos.filter { it.tipo == "simple" }.toMutableList()
        val alimentosReceta= AlimentoProvider.alimentos.filter { it.tipo == "receta" }
        val alimentosMenu = AlimentoProvider.alimentos.filter { it.tipo == "menu" }
        val alimentosDietas = AlimentoProvider.alimentos.filter { it.tipo == "dieta" }

        //Creamos los distintos adapter
        adapterAlimento = DetalleAdapter(alimentosSimples, onClickDelete = {position -> showDeleteConfirmationDialog(position)})
        adapterReceta = DetalleAdapter(alimentosReceta, onClickDelete = {position -> showDeleteConfirmationDialog(position)})
        adapterMenu = DetalleAdapter(alimentosMenu, onClickDelete = {position -> showDeleteConfirmationDialog(position)})
        adapterDieta = DetalleAdapter(alimentosDietas, onClickDelete = {position -> showDeleteConfirmationDialog(position)})

        setupRecyclerViews("simple")
        initListener()
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

        builder.setPositiveButton("Sí") { dialog, which ->
            onClickDelete(position)
        }

        builder.setNegativeButton("No") { dialog, which ->
        }

        val dialog = builder.create()
        dialog.show()
    }

    //Logica para borrar elemento de la lista
    private fun onClickDelete(position: Int) {
        librosMutableList.removeAt(position)
        adapterAlimento.notifyItemRemoved(position)
        adapterReceta.notifyItemRemoved(position)
        adapterMenu.notifyItemRemoved(position)
        adapterDieta.notifyItemRemoved(position)

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
            val nombre = etNombre.text.toString()
            val grHC = etGrHC.text.toString().toDoubleOrNull() ?: 0.0
            val grLip = etGrLip.text.toString().toDoubleOrNull() ?: 0.0
            val grPro = etGrPro.text.toString().toDoubleOrNull() ?: 0.0

            //Almacenamos los datos para crear un nuevo alimento
            updateAlimentos(Alimento(nombre, "simple", grHC, grLip, grPro))
            dialog.dismiss()
        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAlimentos(a:Alimento) {
        alimentosSimples.add(1, a)
        binding.rvTipo.adapter?.notifyDataSetChanged()
        llmanager.scrollToPositionWithOffset(1, 20)
    }


}