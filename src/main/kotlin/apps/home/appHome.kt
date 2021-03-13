package apps.home

import headerfotterAppComponent.fotterapp
import headerfotterAppComponent.headerapp
import kotlinx.html.id
import react.*
import react.dom.div
import react.dom.h3
import react.dom.p


external interface AppHomeProps : RProps {
    var title: String
}

fun RBuilder.appHome(handler: AppHomeProps.() -> Unit) = child(appHome) {
    attrs {
        handler()
    }
}

private val appHome =  functionalComponent<AppHomeProps> { props ->
    div ("d-flex flex-column"){
        attrs { id="content-wrapper"}
        div{
            attrs {id="content"}

            headerapp(){
                title=props.title
            }
            //QUI VA INSERITA LA APP
            div("container-fluid") {
                div("row") {
                    div("col") {
                        h3 ("text-dark mb-4"){
                            +"HOME"
                        }
                        div("card shadow") {
                            div("card-header py-3") {
                                p("text-primary m-0 font-weight-bold") {
                                    +"HOME"
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

