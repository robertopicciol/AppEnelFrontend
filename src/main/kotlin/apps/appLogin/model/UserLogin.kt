package apps.appLogin.model

data class UserLogin(@JsName("usernameUL") val usernameUL : String,
                     @JsName("useremailUL") val useremailUL : String,
                     @JsName("userpasswordUL") val userpasswordUL : String) {
}