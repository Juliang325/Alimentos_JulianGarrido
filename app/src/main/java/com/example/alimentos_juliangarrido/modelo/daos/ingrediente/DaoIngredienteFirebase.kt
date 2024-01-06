package com.example.alimentos_juliangarrido.modelo.daos.ingrediente

import com.example.alimentos_juliangarrido.modelo.conexiones.ConexionBD
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoIngrediente

class DaoIngredienteFirebase: InterfaceDaoIngrediente {
    override fun addIngrediente(al: Alimento, ing: Ingrediente) {
        TODO("Not yet implemented")
    }

    override fun getIngredientes(al: Alimento): MutableList<Ingrediente> {
        TODO("Not yet implemented")
    }

    override fun updateIngrediente(al: Alimento, ing: Ingrediente) {
        TODO("Not yet implemented")
    }

    override fun deleteAlimento(al: Alimento, ing: Ingrediente) {
        TODO("Not yet implemented")
    }

    override fun createConexion(conexion: ConexionBD) {
        TODO("Not yet implemented")
    }
}