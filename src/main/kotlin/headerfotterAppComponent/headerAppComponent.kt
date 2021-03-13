package headerfotterAppComponent


import react.*
import react.dom.div
import react.dom.h3
import react.dom.nav

external interface HeaderAppProps : RProps {
    var title: String
}


private val headerapp =  functionalComponent<HeaderAppProps> {props ->
    nav( "navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top"){
        div ("container-fluid"){
            h3("text-dark mb-4"){
                +props.title
            }

            div { +"PROVA" }
        }
    }
}

fun RBuilder.headerapp(handler: HeaderAppProps.() -> Unit) = child(headerapp) {
    attrs {
        handler()
    }
}

