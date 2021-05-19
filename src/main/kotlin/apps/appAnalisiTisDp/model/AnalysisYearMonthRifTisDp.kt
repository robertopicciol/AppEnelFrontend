package apps.appAnalisiTisDp.model

data class AnalysisYearMonthRifTisDp(
    val type : String,
    val dayAnalysis : Int,
    val yearRif : Int,
    val monthRif : Int,
    val voltage : String,
    val towardsEnergy : String,
    val activeF1 : Double,
    val activeF2 : Double,
    val activeF3 : Double,
    val activeTot : Double,
    val nr : Double
)