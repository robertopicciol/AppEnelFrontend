package model

data class RespondBo(@JsName("respesult") val respresult : String,
                     @JsName("respmessage") val respmessage : String) {
    companion object {
        fun emptyRespond() = RespondBo("", "")
    }
}
