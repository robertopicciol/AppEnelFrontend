package apps.appCheckExaRegCurve.model



class InfoChartCheckRegCurve(val type : String?, val minGGAnalisi : Long?, val maxGGAnalisi : Long?, val minAnnomeseIns : Long?, val maxAnnomeseIns : Long?, val stati : Array<String> ) {
    companion object {
        fun newEmptyRegCurveChartInfo()= InfoChartCheckRegCurve(null,null,null,null,null, emptyArray())
    }
}