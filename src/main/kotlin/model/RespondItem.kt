package model


class RespondItem<T>(result : String, message : String, val item : T) : Respond(result,message) {
    fun getJson() : String = JSON.stringify(this)
}