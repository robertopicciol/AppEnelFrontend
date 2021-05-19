package apps.appAnalisiTisDp.model

class AnalysisTisDp(val type : String?,
                    val dayAnalysis : Int?,
                    val year : Array<AnalysisYearMonthRifTisDp>,
                    val yearMonth : Array<AnalysisYearMonthRifTisDp>
)
{
    companion object {
        fun newEmptyAnalisiTisDp(type : String?,dayAnalysis : Int?)= AnalysisTisDp(type, dayAnalysis,emptyArray(),emptyArray())
    }
}