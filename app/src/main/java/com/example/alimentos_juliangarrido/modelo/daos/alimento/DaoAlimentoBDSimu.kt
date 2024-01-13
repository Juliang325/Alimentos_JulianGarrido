package com.example.alimentos_juliangarrido.modelo.daos.alimento

import android.content.Context
import com.example.alimentos_juliangarrido.modelo.conexiones.BDFicheroAlimento
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

open class DaoAlimentoBDSimu(private val context:Context) {

    private val bdFicheroAlimento = BDFicheroAlimento(context)


    fun addAlimento(al: Alimento) {
        val lista=bdFicheroAlimento.leer()
        lista.add(al)
        bdFicheroAlimento.escribir(lista)
    }
     fun getAlimentos(): MutableList<Alimento> {
        return bdFicheroAlimento.leer()
    }
     fun getAlimentos(tipo: String): MutableList<Alimento> {
        val lista=bdFicheroAlimento.leer()
        return lista.filter{it.tipo==tipo} as MutableList<Alimento>
    }
     fun getAlimento(al: Alimento): Alimento {
        val lista = bdFicheroAlimento.leer()
        return lista.firstOrNull { it == al } ?: throw NoSuchElementException("El alimento no se encuentra en la base de datos")
    }

     fun updateAlimento(al: Alimento) {
        val lista = bdFicheroAlimento.leer()
        val index = lista.indexOfFirst { it == al }
        if (index != -1) {
            lista[index] = al
            bdFicheroAlimento.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

     fun deleteAlimento(al: Alimento) {
        val lista = bdFicheroAlimento.leer()
        if (lista.remove(al)) {
            bdFicheroAlimento.escribir(lista)
        } else {
            throw NoSuchElementException("El alimento no se encuentra en la base de datos")
        }
    }

}