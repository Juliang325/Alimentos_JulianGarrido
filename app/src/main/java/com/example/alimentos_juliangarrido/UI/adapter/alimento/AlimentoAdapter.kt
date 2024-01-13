package com.example.alimentos_juliangarrido.UI.adapter.alimento

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alimentos_juliangarrido.R
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class AlimentoAdapter(private val alimentos: List<Alimento>) : RecyclerView.Adapter<AlimentoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlimentoViewHolder(layoutInflater.inflate(R.layout.item_alimento, parent, false))
    }


    override fun onBindViewHolder(holder: AlimentoViewHolder, position: Int) {
        val item = alimentos[position]
        holder.render(item)
    }


    override fun getItemCount(): Int = alimentos.size
}
