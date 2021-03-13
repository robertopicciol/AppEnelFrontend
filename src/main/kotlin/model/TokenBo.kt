package model


class TokenBo(@JsName("usernameT") var usernameT : String?,
                   @JsName("emailT") var emailT : String?,
                   @JsName("rolesT") var rolesT : Array<String>?,
                   @JsName("tokenT") var tokenT : String?) {
    fun setToken(token : TokenBo){
        console.log("SSS " + token.usernameT)
        this.usernameT = token.usernameT
        console.log("2SSS ")
        this.emailT = token.emailT
        console.log("3SSS AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        if (token.rolesT != null){
            console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA3SSS ")
            for (i in token.rolesT!!) {
                console.log("zzzz $i")
            }
        }
        this.rolesT = token.rolesT?.copyOf()
        console.log("4SSS ")
        this.tokenT = token.tokenT
        console.log("5SSS ")
    }

    companion object {
        fun newEmptyToken()= TokenBo(null,null,null,null)
    }
}