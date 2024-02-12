package com.example.alimentos_juliangarrido.UI.fragments.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alimentos_juliangarrido.modelo.daos.alimento.DaoAlimentoBDSimu
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    var daoAlimentos = DaoAlimentoBDSimu(application)

    var alimentoMutableList = MutableLiveData<MutableList<Alimento>>()



    init{
        alimentoMutableList.value = daoAlimentos.getAlimentos()
    }
}


