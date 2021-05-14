
import apps.appLogin.appLogin
import kotlinx.browser.document
import kotlinx.browser.window
import model.AppRoute
import model.Token
import react.*
import react.dom.*
import react.router.dom.*

object UrlRestApi {
    const val urlToken = "http://localhost:8080/appEnel/login"
    const val urlRegister = "http://localhost:8080/appEnel/registerUser"
    const val urlRegCurveChartInfo = "http://localhost:8080/appEnel/appChartRegCurveInfo"
    const val urlRegCurveChartData = "http://localhost:8080/appEnel/appChartRegCurveData"
    const val urlAnalisiTisDpChartInfo = "http://localhost:8080/appAnalisiTisInfo"
    const val urlAnalisiTisDpAnnoMeseChartData = "http://localhost:8080/appEnel/appAnalisiTisAnnoMeseData"
    const val urlAnalisiTisDpAnnoChartData = "http://localhost:8080/appEnel/appAnalisiTisAnnoData"
}

object RouterApp {
   val routers = listOf(
       AppRoute("appHome", "appHome","Dashboard", "fas fa-tachometer-alt", "Home", true),
       AppRoute("appUsers", "appUsers","Users", "fas fa-tachometer-alt", "Users", false),
       AppRoute("appRegisterUser", "appRegisterUser","Register User", "fas fa-tachometer-alt", "Register User", false),
       AppRoute("appSqlScriptOracle", "appSqlScriptOracle","Oracle Script", "fas fa-user", "Oracle Call Procedure", false),
       AppRoute("appConfigSqlScriptOracle", "appConfigSqlScriptOracle","Config Oracle Script", "fas fa-user", "Config Oracle Call Procedure", false),
       AppRoute("appAnalisiTisDp", "appAnalisiTisDp","Analisi Tis DP", "fas fa-user", "Analisi Tis Dp", false),
       AppRoute("appCheckExaRegCurve", "appCheckExaRegCurve","Chart Reg Curve", "fas fa-tachometer-alt", "Chart Reg Curve", true)
   )
}


//val contextUser = createContext(UserApp("Pippo"))

val contextToken = createContext(Token.newEmptyToken())

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            startApp()
            //appWithRouter()
        }
    }
}

private fun RBuilder.startApp() = child(startApp) {
}

private val startApp =  functionalComponent<RProps> {
    contextToken.Provider {
        attrs.value = Token.newEmptyToken()
        mainRouter{
            routers = RouterApp.routers
        }
    }
}



external interface MainRouterProps : RProps {
    var routers : List<AppRoute>
}

fun RBuilder.mainRouter(handler: MainRouterProps.() -> Unit) = child(mainRouter) {
    attrs {
        handler()
    }
}



private val mainRouter = functionalComponent<MainRouterProps> { props ->
    val token = useContext(contextToken)
    div {
        console.log("AAAAA ")
        browserRouter {
            switch {
                console.log("CCCCCC ")
                route("/appEnel/prova") {
                    console.log("BBBBBBB " )
                    runEnelApps { routerApp = props.routers; title = "MAIN" }
                }
                route("/appEnel/login") {
                    console.log("DDDDDDDDDDDD ")
                    appLogin()
                }
                route("/appEnel/apps") {
                    console.log("BBBBBBB " )
                    if (token.token != null) runEnelApps { routerApp = props.routers; title = "MAIN" } else redirect(to = "/appEnel/login")
                }
                route("/appEnel/*") {
                    console.log("AAAAA ")
                    if (token.token != null) redirect(to = "/appEnel/apps") else redirect(to = "/appEnel/login")

                }
                route("/*") {
                    console.log("AAAAA " )
                    redirect(to = "/appEnel/prova")
                    //if (token.tokenT != null) redirect(to = "/appEnel/apps") else redirect(to = "/appEnel/login")

                }
            }
        }
    }
}


/*
fun RBuilder.authappmain(handler: AuthAppProps.() -> Unit) = child(authappmain) {
    attrs {
        handler()
    }
}

private val authappmain =  functionalComponent<AuthAppProps> { props ->
    val bodyel = document.getElementById("page-top") as HTMLBodyElement
    div {
        attrs { id = "wrapper" }
        if (props.routeApp != "login"  && props.routeApp != "register"){
            bodyel.removeClass("bg-gradient-primary")
            navbar {
                title = "ROBY"
                listRoute = RouterApp.routers
            }
        } else {
            bodyel.addClass("bg-gradient-primary")
        }
        when(props.routeApp) {
            "Home" -> apphome { title = "HOME" }
            "OracleScript" -> appsqlscriptoracle { title = "ORACLE SCRIPT" }
            "OracleConfigScript" -> appconfigsqlscript { title = "ORACLE SCRIPT" }
            "login" -> loginapp()
            "register" -> registeruser()
            else -> loginapp()
        }
    }
}

*/

