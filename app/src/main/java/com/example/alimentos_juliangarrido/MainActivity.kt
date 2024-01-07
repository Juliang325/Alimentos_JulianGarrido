package com.example.alimentos_juliangarrido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.alimentos_juliangarrido.modelo.conexiones.BDFicheroAlimento
import com.example.alimentos_juliangarrido.modelo.conexiones.BDFirebase
import com.example.alimentos_juliangarrido.modelo.conexiones.ConexionBD
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import com.example.alimentos_juliangarrido.modelo.entities.Ingrediente
import com.example.alimentos_juliangarrido.modelo.factoria.AbstractFactoryDaos
import com.example.alimentos_juliangarrido.modelo.interfaces.InterfaceDaoAlimento

class MainActivity : AppCompatActivity() {

    lateinit var alimento: Alimento
    lateinit var daoAlimento:InterfaceDaoAlimento
    lateinit var conexion: ConexionBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipoBD="fichero"
        val fdao = AbstractFactoryDaos.createFactory(tipoBD)
        if (fdao != null) {
            daoAlimento = fdao.createDaoAlimentos()
            when (tipoBD){
                "fichero"-> conexion = BDFicheroAlimento(this)
                "firebase" -> conexion = BDFirebase(this)
            }

            daoAlimento.createConexion(conexion)
            //pruebaModelo()
            mostrar()
        }




    }

    private fun mostrar(){
        val alimentosList = daoAlimento.getAlimentos()

        for (alimento in alimentosList) {
            Log.d("ListaAlimentos", alimento.toString())
        }
    }


    private fun pruebaModelo() {

        var huevo=Alimento("huevo",grHC=10.0)
        daoAlimento.addAlimento(huevo)
        var patatas=Alimento("patatas",grHC=10.0)
        daoAlimento.addAlimento(patatas)
        var pan=Alimento("pan",grHC=10.0)
        daoAlimento.addAlimento(pan)
        var tortilla=Alimento("tortilla",tipo="receta")
        tortilla.addIngrediente(Ingrediente(huevo,200.0))
        tortilla.addIngrediente(Ingrediente(patatas,200.0))
        daoAlimento.addAlimento(tortilla)
        var menu=Alimento("menu",tipo="menu")
        menu.addIngrediente(Ingrediente(tortilla,200.0))
        menu.addIngrediente(Ingrediente(pan,10.0))
        daoAlimento.addAlimento(menu)
        var dieta=Alimento("dieta",tipo="dieta")
        dieta.addIngrediente(Ingrediente(menu))
        dieta.addIngrediente(Ingrediente(pan,100.0))
        //dieta.recalcula()
        daoAlimento.addAlimento(dieta)

    }
}