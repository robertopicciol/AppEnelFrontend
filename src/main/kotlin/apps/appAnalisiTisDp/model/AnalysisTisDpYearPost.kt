package apps.appAnalisiTisDp.model

data class AnalysisTisDpYearPost(val type : String, val yearMonthAnalysis : Int) {
    fun getJson() : String = JSON.stringify(this)
}