package model

data class RespondBo(@JsName("respesult") val result : String,
                     @JsName("respmessage") val message : String) {
    companion object {
        fun emptyRespond() = RespondBo("", "")
    }
}
