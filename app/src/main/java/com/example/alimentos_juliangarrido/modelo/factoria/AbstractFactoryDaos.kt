package com.example.alimentos_juliangarrido.modelo.factoria

import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoAlimento
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoIngrediente

abstract class AbstractFactoryDaos {

    abstract fun createDaoAlimentos(): InterfaceDaoAlimento
    abstract fun createDaoIngredientes(): InterfaceDaoIngrediente

    companion object {

        fun createFactory(tipo:String): AbstractFactoryDaos? {
            return when (tipo){
                "simu"-> FactoryDaoBDsimu()
                "firebase" -> FactoryDaoFirebase()

                else -> null

            }
        }

    }
}