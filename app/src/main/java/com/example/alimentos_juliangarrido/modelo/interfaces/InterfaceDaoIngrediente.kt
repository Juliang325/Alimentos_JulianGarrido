package com.example.alimentos_juliangarrido.modelo.interfaces

import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente

interface InterfaceDaoIngrediente: InterfaceDao {
    fun addIngrediente(al: Alimento,ing: Ingrediente)
    fun getIngredientes(al:Alimento):MutableList<Ingrediente>
    fun updateIngrediente(al: Alimento, ing:Ingrediente)
    fun deleteAlimento(al: Alimento, ing:Ingrediente)
}