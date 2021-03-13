package model

data class RespondItemBo<T>(@JsName("resultRI") val resultRI : String,
                            @JsName("messageRI") val messageRI : String,
                            @JsName("itemRI") val itemRI: T) {
}
