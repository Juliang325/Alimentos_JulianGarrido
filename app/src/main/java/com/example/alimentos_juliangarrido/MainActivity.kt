package com.example.alimentos_juliangarrido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.alimentos_juliangarrido.UI.fragments.MainFragment
import com.example.alimentos_juliangarrido.databinding.ActivityMainBinding
import com.example.alimentos_juliangarrido.modelo.conexiones.BDFicheroAlimento
import com.example.alimentos_juliangarrido.modelo.daos.alimento.DaoAlimentoBDSimu
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente

class MainActivity : AppCompatActivity() {
    

    private lateinit var binding: ActivityMainBinding
    lateinit var alimento: Alimento
    val fragment = MainFragment() // Crear una instancia del Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = applicationContext
        val daoAlimento = DaoAlimentoBDSimu(context)


        // Crear un alimento
        //val manzana = Alimento("Manzana", "simple", 12.4, 0.2, 0.3)
        //daoAlimento.addAlimento(manzana)

        // Leer todos los alimentos
        val alimentos = daoAlimento.getAlimentos()

        for (alimento in alimentos) {
            Log.d("ListaAlimentos", alimento.toString())
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.navHostFragment, fragment)
            .commit()

    }

    private fun mostrar(){

    }


    private fun pruebaModelo() {




    }
}