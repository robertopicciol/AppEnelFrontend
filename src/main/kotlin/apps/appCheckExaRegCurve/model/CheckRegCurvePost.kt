package apps.appCheckExaRegCurve.model

data class CheckRegCurvePost(val type : String, val annomeseIns : Int) {
    fun getJson() : String = JSON.stringify(this)
}