package com.example.alimentos_juliangarrido.UI.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.alimentos_juliangarrido.databinding.ItemAlimentoBinding
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class AlimentoViewHolder(view: View): ViewHolder(view) {


    val binding = ItemAlimentoBinding.bind(view)



    fun render(alimento: Alimento){
        binding.tvName.text = alimento.nombre
    }
}
