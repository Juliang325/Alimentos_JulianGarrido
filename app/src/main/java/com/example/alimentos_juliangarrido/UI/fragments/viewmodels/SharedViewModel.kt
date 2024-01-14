package com.example.alimentos_juliangarrido.UI.fragments.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class SharedViewModel : ViewModel() {
    val alimentoMutableList = MutableLiveData<MutableList<Alimento>>()
}

