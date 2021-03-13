package apps.utility

import kotlinx.html.role
import react.RBuilder
import react.RProps
import react.child
import react.dom.div
import react.dom.span
import react.functionalComponent

fun RBuilder.spinnerComponent() = child(spinnerComponent) {
}


private val spinnerComponent =  functionalComponent<RProps> {

 div {
    div("spinner-grow text-primary"){
        attrs.role = "status"
        span("sr-only"){
            +"Loading..."
        }
    }
    div("spinner-border text-success"){
        attrs.role = "status"
        span("sr-only"){
            +"Loading..."
        }
    }
    div("spinner-grow text-info"){
        attrs.role = "status"
        span("sr-only"){
            +"Loading..."
        }
    }
 }
}
