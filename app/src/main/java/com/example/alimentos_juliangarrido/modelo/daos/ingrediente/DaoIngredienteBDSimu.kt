package com.example.alimentos_juliangarrido.modelo.daos.ingrediente

import android.content.Context
import com.example.alimentos_juliangarrido.modelo.conexiones.BDFicheroAlimento
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente

class DaoIngredienteBDSimu(private val context:Context) {

    private val bdFicheroAlimento = BDFicheroAlimento(context)


     fun addIngrediente(al: Alimento, ing: Ingrediente) {
        val lista = bdFicheroAlimento.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        if (ingredientesAlimento != null) {
            ingredientesAlimento.ingredientes.add(ing)
            al.addIngrediente(ing)
            bdFicheroAlimento.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

     fun getIngredientes(al: Alimento): MutableList<Ingrediente> {
        val lista = bdFicheroAlimento.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        return (ingredientesAlimento?.ingredientes ?: mutableListOf()) as MutableList<Ingrediente>
    }

     fun updateIngrediente(al: Alimento, ing: Ingrediente) {
        val lista = bdFicheroAlimento.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        if (ingredientesAlimento != null) {
            val index = ingredientesAlimento.ingredientes.indexOfFirst { it == ing }
            if (index != -1) {
                ingredientesAlimento.ingredientes[index] = ing
                al.recalcula() // Recalcular cantidades y calorías del alimento
                bdFicheroAlimento.escribir(lista)
            } else {
                throw NoSuchElementException("El ingrediente no se encuentra en la base de datos")
            }
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

     fun deleteAlimento(al: Alimento, ing: Ingrediente) {
        val lista = bdFicheroAlimento.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        if (ingredientesAlimento != null) {
            if (ingredientesAlimento.ingredientes.remove(ing)) {
                al.recalcula() // Recalcular cantidades y calorías del alimento
                bdFicheroAlimento.escribir(lista)
            } else {
                throw NoSuchElementException("El ingrediente no se encuentra en la base de datos")
            }
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }
}