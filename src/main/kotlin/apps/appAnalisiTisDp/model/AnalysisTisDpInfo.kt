package apps.appAnalisiTisDp.model

class AnalysisTisDpInfo(val type : String?,
                        val yearMonthAnalysis : Array<Int>,
                        val voltage : Array<String>,
                        val towardsEnergy : Array<String>,
                        val yearRif : Array<Int>,
                        val maiYearMonthRif : Int?,
                        val maxYearMonthRif : Int?
                         ) {
    fun getJson() : String = JSON.stringify(this)
    companion object {
        fun newEmptyAnalisiTisDpInfo()= AnalysisTisDpInfo(null, emptyArray(),emptyArray(),emptyArray(),emptyArray(),null,null)
    }
}