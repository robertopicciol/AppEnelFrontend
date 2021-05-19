
import apps.appAnalisiTisDp.appAnalysisTisDp
import apps.appCheckExaRegCurve.appCheckExaRegCurve
import apps.appManageUsers.appAllUsers
import apps.appManageUsers.appRegisterUser
import apps.appScriptRunOracle.appConfigSqlScriptOracle
import apps.appScriptRunOracle.appSqlScriptOracle
import apps.home.appHome
import kotlinx.browser.document
import kotlinx.dom.removeClass
import kotlinx.html.id
import model.AppRoute
import navbarAppComponent.navbar
import org.w3c.dom.HTMLBodyElement
import react.*
import react.dom.div
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch
import react.router.dom.useRouteMatch


external interface RunEnelAppsProps : RProps {
    var title: String
    var routerApp : List<AppRoute>
}

fun RBuilder.runEnelApps(handler: RunEnelAppsProps.() -> Unit) = child(runEnelApps) {
    attrs {
        handler()
    }
}



private val runEnelApps =  functionalComponent<RunEnelAppsProps> { props ->
    val match = useRouteMatch<RProps>() ?: return@functionalComponent
    console.log("AA URL ${match.url} path ${match.path}")
    val bodyel = document.getElementById("page-top") as HTMLBodyElement
    bodyel.removeClass("bg-gradient-primary")

    console.log("WWWWWWWWWW " + match.path)

    div {
        attrs { id = "wrapper" }

        navbar {
            title = props.title
            url = match.url
            listRoute = props.routerApp
        }

        switch {
            route("${match.path}/appUsers") {
                console.log("IN USERS ")
                appAllUsers { title = "USERS"
                } }
            route("${match.path}/appRegisterUser") {
                console.log("IN REGISTER USER ")
                appRegisterUser { title = "REGISTER USER"
                } }
            route("${match.path}/appSqlScriptOracle") {
                console.log("ORACLE SCRIPT " )
                appSqlScriptOracle{title = "ORACLE SCRIPT"}}
            route("${match.path}/appConfigSqlScriptOracle") {
                console.log("CONFIG ORACLE SCRIPT " )
                appConfigSqlScriptOracle{title = "CONFIG ORACLE SCRIPT"}}

            route("${match.path}/appCheckExaRegCurve") {
                console.log("CHART " )
                appCheckExaRegCurve{title= "Check Reg Curve"}}
            route("${match.path}/appAnalysisTisDp") {
                console.log("TIS DP " )
                appAnalysisTisDp{title= "TIS DP"}}
            route("${match.path}/appHome") {
                console.log("IN HOME " )
                appHome{title= "HOME"}}
            route("*") {
                console.log("NULLA ")
                redirect(to="${match.path}/appHome")}
        }
    }
}
