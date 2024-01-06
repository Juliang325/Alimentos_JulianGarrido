package com.example.alimentos_juliangarrido.modelo.daos.alimento

import com.example.alimentos_juliangarrido.modelo.conexiones.BDFicheroAlimento
import com.example.alimentos_juliangarrido.modelo.conexiones.ConexionBD
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoAlimento

open class DaoAlimentoBDSimu: InterfaceDaoAlimento {
    lateinit var conexion: BDFicheroAlimento

    override fun createConexion(con: ConexionBD) {
        conexion = con as BDFicheroAlimento
    }
    override fun addAlimento(al: Alimento) {
        val lista=conexion.leer()
        lista.add(al)
        conexion.escribir(lista)
    }
    override fun getAlimentos(): MutableList<Alimento> {
        return conexion.leer()
    }
    override fun getAlimentos(tipo: String): MutableList<Alimento> {
        val lista=conexion.leer()
        return lista.filter{it.tipo==tipo} as MutableList<Alimento>
    }
    override fun getAlimento(al: Alimento): Alimento {
        val lista = conexion.leer()
        return lista.firstOrNull { it == al } ?: throw NoSuchElementException("El alimento no se encuentra en la base de datos")
    }

    override fun updateAlimento(al: Alimento) {
        val lista = conexion.leer()
        val index = lista.indexOfFirst { it == al }
        if (index != -1) {
            lista[index] = al
            conexion.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

    override fun deleteAlimento(al: Alimento) {
        val lista = conexion.leer()
        if (lista.remove(al)) {
            conexion.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

}