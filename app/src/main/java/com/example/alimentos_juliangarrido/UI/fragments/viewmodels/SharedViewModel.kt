package com.example.alimentos_juliangarrido.UI.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente

class SharedViewModel : ViewModel() {

    var listaAlimentos: MutableList<Alimento> = mutableListOf()
    var listaIngredientes: MutableList<Ingrediente> = mutableListOf()
}