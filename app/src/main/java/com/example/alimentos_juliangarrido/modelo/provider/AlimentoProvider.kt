package com.example.alimentos_juliangarrido.modelo.provider

import com.example.alimentos_juliangarrido.modelo.entities.Alimento

class AlimentoProvider {
    companion object {
         val alimentos = listOf<Alimento>(
             Alimento("patatas", tipo = "simple", grHC = 10.0),
             Alimento("huevo", tipo = "simple", grPro = 6.0),
             Alimento("pollo", tipo = "simple", grPro = 20.0, grLip = 5.0),
             Alimento("arroz", tipo = "simple", grHC = 25.0),
             Alimento("ensalada", tipo = "simple", grHC = 5.0, grPro = 2.0),
             Alimento("tortilla", tipo = "receta"),
             Alimento("ensalada cesar", tipo = "receta"),
             Alimento("menú del día", tipo = "menu"),
             Alimento("dieta mediterránea", tipo = "dieta"),
             Alimento("batido de proteínas", tipo = "simple", grPro = 30.0, grHC = 10.0)
         )
    }
}
