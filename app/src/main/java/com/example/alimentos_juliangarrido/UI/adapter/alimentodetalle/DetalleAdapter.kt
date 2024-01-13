package com.example.alimentos_juliangarrido.UI.adapter.alimentodetalle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alimentos_juliangarrido.R
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class DetalleAdapter(private val alimentos: List<Alimento>, private val onClickDelete:(Int) -> Unit) : RecyclerView.Adapter<DetalleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DetalleViewHolder(layoutInflater.inflate(R.layout.item_detalle, parent, false))
    }


    override fun onBindViewHolder(holder: DetalleViewHolder, position: Int) {
        val item = alimentos[position]
        holder.render(item, onClickDelete)
    }


    override fun getItemCount(): Int = alimentos.size
}
