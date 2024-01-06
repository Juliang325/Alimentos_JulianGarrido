package com.example.alimentos_juliangarrido.modelo.interfaces

import com.example.alimentos_juliangarrido.modelo.entities.Alimento

interface InterfaceDaoAlimento:InterfaceDao {
    fun addAlimento(al: Alimento)
    fun getAlimentos():MutableList<Alimento>
    fun getAlimentos(tipo:String):MutableList<Alimento>
    fun getAlimento(al: Alimento):Alimento
    fun updateAlimento(al:Alimento)
    fun deleteAlimento(al:Alimento)
}