package apps.appCheckExaRegCurve.model

data class CheckRegCurvePost(val type : String, val annomeseIns : Long) {
    fun getJson() : String = JSON.stringify(this)
}