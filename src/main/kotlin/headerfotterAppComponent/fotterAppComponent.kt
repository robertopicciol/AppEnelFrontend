package headerfotterAppComponent

import react.RBuilder
import react.RProps
import react.child
import react.dom.div
import react.dom.footer
import react.dom.span
import react.functionalComponent

fun RBuilder.fotterapp() = child(fotterapp) {
}

private val fotterapp =  functionalComponent<RProps> {
    footer ("bg-white sticky-footer"){
        div ("container my-auto"){
            div ("text-center my-auto copyright"){
                span{
                    +"Copyright © Brand 2021"
                }
            }
        }
    }
}

