import apps.appManageUsers.appTableUsers
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
            route("${match.path}/appUsers") {appTableUsers{title = "USERS"}}
            route("${match.path}/appSqlScriptOracle") {appSqlScriptOracle{title = "ORACLE SCRIPT"}}
            route("${match.path}/appConfigSqlScriptOracle") {appConfigSqlScriptOracle{title = "CONFIG ORACLE SCRIPT"}}
            route("${match.path}/appHome") {appHome{title= "HOME"}}
            route("*") {redirect(to="${match.path}/appHome")}
        }
    }
}
