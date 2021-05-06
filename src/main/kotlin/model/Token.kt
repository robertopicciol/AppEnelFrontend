package model


class Token(var username : String?,
            var email : String?,
            var roles : Array<String>?,
            var token : String?) {

    fun setToken(token : Token){
        this.username = token.username
        this.email = token.email
        this.roles = token.roles?.copyOf()
        this.token = token.token
    }

    fun getJson() : String = JSON.stringify(this)

    companion object {
        fun newEmptyToken()= Token(null,null,null,null)
    }
}