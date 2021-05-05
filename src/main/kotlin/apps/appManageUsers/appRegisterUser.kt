package apps.appManageUsers


import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction
import kotlinx.html.role
import apps.appManageUsers.model.UserRegister
import model.RespondBo
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.fetch.RequestInit
import react.*
import react.dom.*
import kotlin.js.json


suspend fun saveUser(user : UserRegister) : RespondBo {
    try {
        console.log("REGISTER " + JSON.stringify(user))
        val response  = window.fetch(UrlRestApi.urlRegister, object: RequestInit {
            override var method: String? = "POST"
            override var body: dynamic = JSON.stringify(user)
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()
        /* .json()
         .await()
         .unsafeCast<String>()*/
        val resp = response.json().await().unsafeCast<RespondBo>()
        return if (response.ok) resp else RespondBo("KO",response.statusText)
    } catch(e:Throwable){
        console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        return RespondBo("KO",e.message ?: "Exception")
    }


}

fun RBuilder.appRegisterUser() = child(appRegisterUser) {
}

private val appRegisterUser =  functionalComponent<RProps> {
    val (regfirstname, setRegFirstname) = useState("")
    val (reglastname, setRegLastname) = useState("")
    val (regusername, setRegUsername) = useState("")
    val (regemail, setRegEmail) = useState("")
    val (regpassword, setRegPassword) = useState("")
    val (regpassword_retype,setRegPassword_retype)= useState("")
    val (regRoles,setRegRoles) = useState( mapOf("ADMIN" to true, "USER" to false))
    val (regMessageInput, setRegMessageInput) = useState(RespondBo.emptyRespond())

    val submitRegister = { event : Event ->
        event.preventDefault()
        if ((regusername == "" && regemail == "" ) || regpassword == "" || reglastname == "" || regfirstname == "") {
            setRegMessageInput(RespondBo("KO","The fileds must be not null"))
        } else {
            if (regpassword_retype != regpassword){
                setRegMessageInput(RespondBo("KO","Passwords are not the same."))
            } else {
                val user = UserRegister(regfirstname,reglastname,regusername,regemail,regpassword,regRoles.filterValues{ it }.keys)
                console.log("USER: $user")
                setRegMessageInput(RespondBo.emptyRespond())
                val mainScope = MainScope()
                mainScope.launch {
                    try {
                        setRegMessageInput(saveUser(user))
                    } catch (e: Throwable) {
                        setRegMessageInput(RespondBo("KO", e.message.toString()))
                    }
                }
                Unit
            }
        }
    }

    div ("card shadow-lg o-hidden border-0 my-5") {
        div ("card-body p-0") {
            div ("row"){
                div ("col-lg-5 d-none d-lg-flex"){
                    div ("flex-grow-1 bg-register-image registerclass"){
                    }
                }
                div ("col-lg-7"){
                    div ("p-5"){
                        div ("text-center"){
                            h4 ("text-dark mb-4"){
                                +"Create an Account!"
                            }
                        }
                        form (classes="user",action="#",method = null){
                            attrs.onSubmitFunction = submitRegister
                            div ("form-group row"){
                                div ("col-sm-6 mb-3 mb-sm-0"){
                                    input (type= InputType.text,classes="form-control form-control-user",name="regfirstname"){
                                        attrs  {
                                            id="regfirstname"
                                            placeholder="First Name"
                                            required=true
                                            value = regfirstname
                                            onChangeFunction = {event ->
                                                val target = event.target as HTMLInputElement
                                                setRegFirstname(target.value)
                                            }

                                        }
                                    }
                                }
                                div ("col-sm-6"){
                                    input (type= InputType.text,classes="form-control form-control-user",name="reglastname"){
                                        attrs  {
                                            id="reglastname"
                                            placeholder="Last Name"
                                            required=true
                                            value = reglastname
                                            onChangeFunction = {event ->
                                                val target = event.target as HTMLInputElement
                                                setRegLastname(target.value)
                                            }
                                        }
                                    }
                                }
                            }
                            div ("form-group"){
                                input (type= InputType.email,classes="form-control form-control-user",name="regemail"){
                                    attrs {
                                        id="regemail"
                                        placeholder="Insert user email"
                                        required=true
                                        value = regemail
                                        onChangeFunction = {event ->
                                            val target = event.target as HTMLInputElement
                                            setRegEmail(target.value)
                                        }
                                    }
                                }
                            }
                            div ("form-group"){
                                input (type= InputType.text,classes="form-control form-control-user",name="regusername"){
                                    attrs {
                                        id="regusername"
                                        placeholder="Insert user"
                                        required=true
                                        value = regusername
                                        onChangeFunction = {event ->
                                            val target = event.target as HTMLInputElement
                                            setRegUsername(target.value)
                                        }
                                    }
                                }
                            }
                            div ("form-group row"){
                                div ("col-sm-6 mb-3 mb-sm-0"){
                                    input (type= InputType.password,classes="form-control form-control-user",name="regpassword"){
                                        attrs  {
                                            id="regpassword"
                                            placeholder="Insert password"
                                            required=true
                                            value = regpassword
                                            onChangeFunction = {event ->
                                                val target = event.target as HTMLInputElement
                                                setRegPassword(target.value)
                                            }
                                        }
                                    }
                                }
                                div ("col-sm-6"){
                                    input (type= InputType.password,classes="form-control form-control-user",name="regpassword_retype"){
                                        attrs  {
                                            id="regpassword_retype"
                                            placeholder="Retype password"
                                            required=true
                                            value = regpassword_retype
                                            onChangeFunction = {event ->
                                                val target = event.target as HTMLInputElement
                                                setRegPassword_retype(target.value)
                                            }
                                        }
                                    }
                                }
                            }
                            div ("form-group row"){
                                regRoles.forEach {
                                    div ("col-sm-3 mb-3 mb-sm-0") {
                                        div("form-check") {
                                            input(type = InputType.checkBox, classes = "form-check-input", name="regCheck${it.key}") {
                                                attrs {
                                                    id = "regCheck${it.key}"
                                                    value=it.key
                                                    checked=it.value
                                                    onChangeFunction = {event ->
                                                        val target = event.target as HTMLInputElement
                                                        val roles = regRoles + mapOf(value to target.checked)
                                                        setRegRoles(roles)
                                                    }
                                                }
                                            }
                                            label("form-check-label") {
                                                +it.key
                                            }
                                        }
                                    }
                                }
                            }
                            button (classes="btn btn-primary btn-block text-white btn-user", type= ButtonType.submit){
                                +"Register Account"
                            }
                            hr{}
                            if (regMessageInput.result != "") {
                                if (regMessageInput.result == "KO") {
                                    div(classes = "alert alert-danger") {
                                        attrs.role = "alert"
                                        +regMessageInput.message
                                    }
                                } else {
                                    div(classes = "alert alert-success") {
                                        attrs.role = "alert"
                                        +regMessageInput.message
                                    }
                                }
                            }
                            hr{}
                        }

                    }

                }
            }
        }
    }

}