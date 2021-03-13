package apps.appScriptRunOracle


import headerfotterAppComponent.fotterapp
import headerfotterAppComponent.headerapp
import kotlinx.html.id
import react.*
import react.dom.div
import react.dom.h3
import react.dom.p


external interface AppSqlScripOracleProps : RProps {
    var title: String
}


fun RBuilder.appSqlScriptOracle(handler: AppSqlScripOracleProps.() -> Unit) = child(appSqlScriptOracle) {
    attrs {
        handler()
    }
}

private val appSqlScriptOracle =  functionalComponent<AppSqlScripOracleProps> { props ->
    div ("d-flex flex-column"){
        attrs { id="content-wrapper"}
        div{
            attrs {id="content"}

            headerapp(){
                title=props.title
            }
            div("container-fluid") {
                div("row") {
                    div("col") {
                        h3 ("text-dark mb-4"){
                            +"Oracle Call Procedure"
                        }
                        div("card shadow") {
                            div("card-header py-3") {
                                p("text-primary m-0 font-weight-bold") {
                                    +"Procedure Frame"
                                }
                            }
                            div("card-body") {

                            }
                        }
                    }

                }
            }
        }
        fotterapp()
    }
}

