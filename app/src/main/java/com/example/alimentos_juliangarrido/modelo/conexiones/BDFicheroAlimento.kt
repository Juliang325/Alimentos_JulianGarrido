package com.example.alimentos_juliangarrido.modelo.conexiones

import android.content.Context
import com.example.alimentos_juliangarrido.modelo.entities.Alimento
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class BDFicheroAlimento(private val context: Context){
    val nombre="alimentos.dat"
    fun escribir(lista:MutableList<Alimento>,nombreArchivo: String=nombre) {
        try {
            val fileOutputStream = context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(lista)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun leer():MutableList<Alimento> {
        var lista: MutableList<Alimento>? = null
        try {
            val fileInputStream = context.openFileInput(nombre)
            val objectInputStream = ObjectInputStream(fileInputStream)
            lista = objectInputStream.readObject() as MutableList<Alimento>
            objectInputStream.close()
            fileInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return lista ?: mutableListOf()
    }
    fun borrarArchivos() {
        val archivo = context.getFileStreamPath(nombre)
        if (archivo.exists()) {
            archivo.delete()
        }
    }
}
