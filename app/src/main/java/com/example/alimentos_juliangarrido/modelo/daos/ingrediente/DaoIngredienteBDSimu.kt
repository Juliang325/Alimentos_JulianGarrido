package com.example.alimentos_juliangarrido.modelo.daos.ingrediente

import com.example.alimentos_juliangarrido.modelo.conexiones.BDFicheroAlimento
import com.example.alimentos_juliangarrido.modelo.conexiones.ConexionBD
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoIngrediente

class DaoIngredienteBDSimu: InterfaceDaoIngrediente {
    lateinit var conexion: BDFicheroAlimento

    override fun createConexion(conexion: ConexionBD) {
        this.conexion = conexion as BDFicheroAlimento
    }

    override fun addIngrediente(al: Alimento, ing: Ingrediente) {
        val lista = conexion.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        if (ingredientesAlimento != null) {
            ingredientesAlimento.ingredientes.add(ing)
            al.addIngrediente(ing) // Actualizar cantidades y calorías del alimento
            conexion.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

    override fun getIngredientes(al: Alimento): MutableList<Ingrediente> {
        val lista = conexion.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        return (ingredientesAlimento?.ingredientes ?: mutableListOf()) as MutableList<Ingrediente>
    }

    override fun updateIngrediente(al: Alimento, ing: Ingrediente) {
        val lista = conexion.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        if (ingredientesAlimento != null) {
            val index = ingredientesAlimento.ingredientes.indexOfFirst { it == ing }
            if (index != -1) {
                ingredientesAlimento.ingredientes[index] = ing
                al.recalcula() // Recalcular cantidades y calorías del alimento
                conexion.escribir(lista)
            } else {
                throw NoSuchElementException("El ingrediente no se encuentra en la base de datos")
            }
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

    override fun deleteAlimento(al: Alimento, ing: Ingrediente) {
        val lista = conexion.leer()
        val ingredientesAlimento = lista.find { it.nombre == al.nombre }
        if (ingredientesAlimento != null) {
            if (ingredientesAlimento.ingredientes.remove(ing)) {
                al.recalcula() // Recalcular cantidades y calorías del alimento
                conexion.escribir(lista)
            } else {
                throw NoSuchElementException("El ingrediente no se encuentra en la base de datos")
            }
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }
}