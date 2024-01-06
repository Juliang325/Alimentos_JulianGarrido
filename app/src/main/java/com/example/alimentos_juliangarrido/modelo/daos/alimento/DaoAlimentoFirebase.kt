package com.example.alimentos_juliangarrido.modelo.daos.alimento

import com.example.alimentos_juliangarrido.modelo.conexiones.ConexionBD
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoAlimento

open class DaoAlimentoFirebase: InterfaceDaoAlimento {

    override fun createConexion(conexion: ConexionBD) {
        TODO("Not yet implemented")
    }

    override fun addAlimento(al: Alimento) {
        TODO("Not yet implemented")
    }

    override fun getAlimentos(): MutableList<Alimento> {
        TODO("Not yet implemented")
    }

    override fun getAlimentos(tipo: String): MutableList<Alimento> {
        TODO("Not yet implemented")
    }

    override fun getAlimento(al: Alimento): Alimento {
        TODO("Not yet implemented")
    }

    override fun updateAlimento(al: Alimento) {
        TODO("Not yet implemented")
    }

    override fun deleteAlimento(al: Alimento) {
        TODO("Not yet implemented")
    }


}