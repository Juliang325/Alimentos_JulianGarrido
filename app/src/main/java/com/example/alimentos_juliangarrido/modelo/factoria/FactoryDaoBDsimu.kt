package com.example.alimentos_juliangarrido.modelo.factoria

import com.example.alimentos_juliangarrido.modelo.daos.alimento.DaoAlimentoBDSimu
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoAlimento
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoIngrediente

class FactoryDaoBDsimu: AbstractFactoryDaos() {

    override fun createDaoAlimentos(): InterfaceDaoAlimento {
        return DaoAlimentoBDSimu()
    }

    override fun createDaoIngredientes(): InterfaceDaoIngrediente {
        TODO("Not yet implemented")
    }

}