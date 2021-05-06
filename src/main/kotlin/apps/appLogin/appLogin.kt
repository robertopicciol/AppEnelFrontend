package apps.appLogin


import apps.appLogin.model.UserLogin
import apps.utility.spinnerComponent
import contextToken
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.dom.addClass
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import model.RespondItem
import model.Token
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.fetch.RequestInit
import react.*
import react.dom.*
import react.router.dom.routeLink
import react.router.dom.useHistory
import kotlin.js.json

fun RBuilder.appLogin() = child(appLogin) {
}

/*

fun runFailingCoroutine() = runBlocking {
    try {
        coroutineScope { async { fail() }.await()  }
    } catch (ex: Throwable) {
        println("Catching ex in runFailingCoroutine(): $ex")
    }
}

jsonAs<RequestInit>().apply {
        method = "POST"
        headers = json().apply {
            this["Content-Type"] = "application/json"
        }
        body = JSON.stringify(json().apply {
            this["clientData"] = "I'm posted by client"
        })
    }


    {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
        body: JSON.stringify({a: 1, b: 'Textual content'})
    }

    object: RequestInit {
        override var method: String? = method
        override var body: dynamic = body
        override var credentials: RequestCredentials? = "same-origin".asDynamic()
        override var headers: dynamic = json("Accept" to "application/json")
    }

URLSearchParams().apply {
                                                        append("userId", user.userName)

                                                        }

'Authorization': 'Bearer ' + this.authToken
override var headers: dynamic = json("Content-Type" to "application/json","Authorization"  to "Bearer " + this.authToken )
 */


suspend fun fetchToken(user : UserLogin) : RespondItem<Token> {
    return try {
        console.log("aaa " + user.getJson())
        val response  = window.fetch(UrlRestApi.urlToken, object: RequestInit {
            override var method: String? = "POST"
            override var body: dynamic = user.getJson()//JSON.stringify(user)
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()

        val token = response.json().await().unsafeCast<RespondItem<Token>>()
        if (response.ok) token else RespondItem("KO", response.statusText, Token.newEmptyToken())
    } catch(e:Throwable){
        console.log(e.message.toString())
        RespondItem("KO", "SERVER PROBLEMS", Token.newEmptyToken())
    }

}



private val appLogin =  functionalComponent<RProps> {
    val (loginusername, setLoginUsername) = useState("")
    val (loginemail, setLoginEmail) = useState("")
    val (loginpassword, setLoginPassword) = useState("")
    val (loginerrorInput, setLoginErrorInput) = useState("")
    val (awaitLogin,setAwaitLogin) = useState(false)
    val bodyel = document.getElementById("page-top") as HTMLBodyElement
    val history = useHistory()
    val token = useContext(contextToken)

    val submitLogin = { event : Event ->
        event.preventDefault()

        if ((loginusername == "" && loginemail == "" ) || loginpassword == "") {
            setLoginErrorInput("Username or Email must be not null or Password must be not null")
        } else {
            setLoginErrorInput("")
            setAwaitLogin(true)

            val mainScope = MainScope()
            mainScope.launch {
                //delay(5000)
                try {
                    val resptoken = fetchToken(UserLogin(loginusername, loginemail, loginpassword))
                    setAwaitLogin(false)
                    console.log("AAAAA result " + resptoken.result)
                    if (resptoken.result == "KO") {
                        setLoginErrorInput(resptoken.message)
                    } else {
                        token.setToken(resptoken.item)

                        if (token.token != null) {
                            history.push( "/appEnel/apps")
                        } else {
                            setLoginErrorInput("TOKEN IS NULL")
                        }
                    }
                } catch (e: Throwable) {
                    console.log("CCCC TOKEN " + e.message.toString())
                    setAwaitLogin(false)
                    setLoginErrorInput( e.message.toString())
                }
            }
            Unit
        }
    }

    bodyel.addClass("bg-gradient-primary")

    div ("row justify-content-center") {
        div ("col-md-9 col-lg-12 col-xl-10") {
            div ("card shadow-lg o-hidden border-0 my-5"){
                div ("card-body p-0"){
                    div ("row"){
                        div ("col-lg-6 d-none d-lg-flex"){
                            div ("flex-grow-1 bg-login-image loginclass"){
                            }
                        }
                        div ("col-lg-6"){
                            div ("p-5"){
                                div ("text-center"){
                                    h4 ("text-dark mb-4"){
                                        +"Welcome Back!"
                                    }
                                }
                                form (classes="user",action="#",method = null){
                                    attrs.onSubmitFunction = submitLogin
                                    div ("form-group"){
                                        input (type= InputType.text,classes="form-control form-control-user",name="loginusername"){
                                            attrs  {
                                                id="loginusername"
                                                placeholder="Enter Username..."
                                                value = loginusername
                                                required= loginemail == ""
                                                onChangeFunction = {event ->
                                                    val target = event.target as HTMLInputElement
                                                    setLoginUsername(target.value)
                                                }
                                            }
                                        }
                                    }
                                    div ("form-group"){
                                        input (type= InputType.email,classes="form-control form-control-user",name="loginemail"){
                                            attrs  {
                                                id="loginemail"
                                                placeholder="Enter Email Address..."
                                                value = loginemail
                                                required= loginusername == ""
                                                onChangeFunction = {event ->
                                                    val target = event.target as HTMLInputElement
                                                    setLoginEmail(target.value)
                                                }
                                            }
                                        }
                                    }
                                    div ("form-group"){
                                        input (type= InputType.password,classes="form-control form-control-user",name="loginpassword"){
                                            attrs {
                                                id="loginpassword"
                                                placeholder="Password"
                                                value = loginpassword
                                                required=true
                                                onChangeFunction = {event ->
                                                    val target = event.target as HTMLInputElement
                                                    setLoginPassword(target.value)
                                                }
                                            }
                                        }
                                    }
                                    button (classes="btn btn-primary btn-block text-white btn-user",type= ButtonType.submit){
                                        +"Login"
                                    }
                                    hr{}
                                    if (loginerrorInput != "") {
                                        div(classes = "alert alert-danger") {
                                            attrs.role = "alert"
                                            +loginerrorInput
                                        }
                                    }
                                    if (awaitLogin) {
                                        spinnerComponent()
                                    }
                                    hr{}
                                }
                                div ("text-center"){
                                    routeLink("/appEnel/forgot-passoword", className = "nav-link") {
                                        span{ +"Forgot password" }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}