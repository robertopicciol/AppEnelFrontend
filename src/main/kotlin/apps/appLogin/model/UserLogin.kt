package apps.appLogin.model

data class UserLogin(val username : String,
                     val email : String,
                     val password : String) {

    fun getJson() : String = JSON.stringify(this)
}



