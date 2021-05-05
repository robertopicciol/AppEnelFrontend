package model

import kotlin.js.Json


class TokenBo(var username : String?,
              var email : String?,
              var roles : Array<String>?,
              var token : String?) {

    fun setToken(token : TokenBo){
        this.username = token.username
        this.email = token.email
        this.roles = token.roles?.copyOf()
        this.token = token.token
    }

    fun getJson() : String = JSON.stringify(this)

    companion object {
        fun newEmptyToken()= TokenBo(null,null,null,null)
    }
}