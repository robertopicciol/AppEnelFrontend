package apps.appAnalisiTisDp

import headerfotterAppComponent.fotterapp
import headerfotterAppComponent.headerapp
import kotlinx.browser.document
import kotlinx.dom.addClass
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.role
import org.w3c.dom.HTMLBodyElement
import react.RBuilder
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent


external interface AppAnalisiTisDpProps : RProps {
    var title: String
}

fun RBuilder.appAnalisiTisDp(handler: AppAnalisiTisDpProps.() -> Unit) = child(appAnalisiTisDp) {
    attrs {
        handler()
    }
}

private val appAnalisiTisDp =  functionalComponent<AppAnalisiTisDpProps> { props ->
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
                            +"Analisi Tis DP"
                        }
                        div("card shadow") {
                            div("card-header py-3") {
                                p("text-primary m-0 font-weight-bold") {
                                    +"Analisi Tis DP"
                                }
                            }
                            div("card-body") {
                                div("row"){
                                    div("col-md-6 text-nowrap"){

                                    }
                                    div("col-md-6"){

                                    }
                                }
                                div("row"){

                                    div("col-md-6"){

                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        fotterapp()
    }
}
