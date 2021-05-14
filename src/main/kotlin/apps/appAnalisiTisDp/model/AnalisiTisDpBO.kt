package com.roby66one.enel.model.analisiTisDp.bo

data class AnalisiTisDpBO(
    val type : String,
    val ggAnalisi : Long,
    val annoRif : Long,
    val meseRif : Long,
    val livTensione : String,
    val versoEnergia : String,
    val attivaF1 : Double,
    val attivaF2 : Double,
    val attivaF3 : Double,
    val attivaTot : Double,
    val nr : Long
)