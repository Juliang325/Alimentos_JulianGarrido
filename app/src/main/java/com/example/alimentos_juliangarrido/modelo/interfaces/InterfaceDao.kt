package com.example.alimentos_juliangarrido.modelo.interfaces

import com.example.alimentos_juliangarrido.modelo.conexiones.ConexionBD

interface InterfaceDao {

    fun createConexion(conexion:ConexionBD)

}