package model

open class Respond(override val result : String, override val message : String) : IRespond {
    companion object {
        fun emptyRespond() = Respond("", "")
    }
}
