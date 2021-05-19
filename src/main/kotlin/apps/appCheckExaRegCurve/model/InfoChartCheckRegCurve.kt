package apps.appCheckExaRegCurve.model



class InfoChartCheckRegCurve(val type : String?,
                             val minGGAnalisi : Int?,
                             val maxGGAnalisi : Int?,
                             val minAnnomeseIns : Int?,
                             val maxAnnomeseIns : Int?,
                             val stati : Array<String>,
                             val yearMonth : Array<Int>
) {
    companion object {
        fun newEmptyRegCurveChartInfo()= InfoChartCheckRegCurve(null,null,null,null,null, emptyArray(),emptyArray())
    }
}