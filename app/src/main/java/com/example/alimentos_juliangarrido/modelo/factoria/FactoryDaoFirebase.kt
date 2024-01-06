package com.example.alimentos_juliangarrido.modelo.factoria

import com.example.alimentos_juliangarrido.modelo.daos.alimento.DaoAlimentoFirebase
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoAlimento
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoIngrediente

class FactoryDaoFirebase: AbstractFactoryDaos() {
    override fun createDaoAlimentos(): InterfaceDaoAlimento {
        return DaoAlimentoFirebase()
    }

    override fun createDaoIngredientes(): InterfaceDaoIngrediente {
        TODO("Not yet implemented")
    }
}