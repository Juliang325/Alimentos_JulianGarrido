package com.example.alimentos_juliangarrido.UI.adapter.alimentodetalle

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.alimentos_juliangarrido.databinding.ItemAlimentoBinding
import com.example.alimentos_juliangarrido.databinding.ItemDetalleBinding
import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class DetalleViewHolder(view: View): ViewHolder(view) {

    val binding = ItemDetalleBinding.bind(view)

    fun render(alimento: Alimento,  onClickDelete: (Int) -> Unit){
        binding.tvNombre.text = alimento.nombre
        binding.tvKcal.text = alimento.Kcal.toString() + " kcal Total"
        binding.tvIngredientes.text = alimento.ingredientes.joinToString { it?.alimento?.nombre ?: "" }
        binding.cardView.setOnClickListener { onClickDelete(adapterPosition) }
    }
}
