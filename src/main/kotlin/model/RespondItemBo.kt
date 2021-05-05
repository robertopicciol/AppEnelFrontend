package model


data class RespondItemBo<T>(val result : String,
                            val message : String,
                            val item : T
){
    fun getJson() : String = JSON.stringify(this)
}