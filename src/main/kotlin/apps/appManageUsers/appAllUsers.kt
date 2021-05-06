package apps.appManageUsers

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


external interface AppAllUsersProps : RProps {
    var title: String
}

fun RBuilder.appAllUsers(handler: AppAllUsersProps.() -> Unit) = child(appAllUsers) {
    attrs {
        handler()
    }
}

private val appAllUsers =  functionalComponent<AppAllUsersProps> { props ->
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
                            +"Users"
                        }
                        div("card shadow") {
                            div("card-header py-3") {
                                p("text-primary m-0 font-weight-bold") {
                                    +"Users"
                                }
                            }
                            div("card-body") {
                                div("row"){
                                    div("col-md-6 text-nowrap"){
                                        div("dataTables_length"){
                                            attrs {id="dataTable_length"
                                                   attributes["aria-controls"] ="dataTable"
                                                }
                                            label{
                                                +"Show "
                                                select("form-control form-control-sm custom-select custom-select-sm") {
                                                    option(){
                                                        attrs.value = "10"
                                                        attrs.selected = true
                                                        +"10"
                                                    }
                                                    option(){
                                                        attrs.value = "25"
                                                        +"25"
                                                    }
                                                    option(){
                                                        attrs.value = "50"
                                                        +"50"
                                                    }
                                                    option(){
                                                        attrs.value = "75"
                                                        +"75"
                                                    }
                                                    option(){
                                                        attrs.value = "100"
                                                        +"100"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div("col-md-6"){
                                        div("text-md-right dataTables_filter" ){
                                            attrs {id="dataTable_filter"}
                                            label{
                                                input(type=InputType.search,classes="form-control form-control-sm"){
                                                    attrs{
                                                        placeholder="Search"
                                                        attributes["aria-controls"] ="dataTable"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                div("table-responsive table mt-2" ){
                                    attrs{
                                        id="dataTable"
                                        role="grid"
                                        attributes["aria-describedby"] ="dataTable_info"
                                    }
                                    table(classes="table my-0" ) {
                                        attrs{
                                            id="dataTable"
                                        }
                                        thead {
                                            tr {
                                                th { +"Name" }
                                                th { +"Position" }
                                                th { +"Office" }
                                                th { +"Age" }
                                                th { +"Start date" }
                                                th { +"Salary" }
                                            }
                                        }
                                        tbody {
                                            for(i in 1 .. 100){
                                                tr {
                                                    td { +"Accountant $i"  }
                                                    td { +"Tokyo $i"  }
                                                    td { +"33 $i"  }
                                                    td { +"2008/11/28 $i"  }
                                                    td { +"162,700 $i"  }
                                                    td { +"aaa $i"  }
                                                }
                                            }
                                        }
                                        tfoot {
                                            tr {
                                                th { +"Name" }
                                                th { +"Position" }
                                                th { +"Office" }
                                                th { +"Age" }
                                                th { +"Start date" }
                                                th { +"Salary" }
                                            }
                                        }
                                    }
                                }
                                div("row"){
                                    div("col-md-6 align-self-center"){
                                        p("dataTables_info" ){
                                            attrs{
                                                id="dataTable_info"
                                                role="status"
                                                attributes["aria-live"] ="polite"
                                            }
                                            +"Showing 1 to 10 of 27"
                                        }
                                    }
                                    div("col-md-6"){
                                        nav("d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers"){
                                            ul("pagination"){
                                                li("page-item disabled"){
                                                    a("page-link"){
                                                        attrs{
                                                            href="#"
                                                            attributes["aria-label"] ="Previous"
                                                        }
                                                        span {
                                                            attrs{
                                                                attributes["aria-hidden"]="true"
                                                            }
                                                            +"«"
                                                        }
                                                    }
                                                }
                                                li("page-item active"){
                                                    a("page-link"){
                                                        attrs {
                                                            href = "#"
                                                        }
                                                        +"1"
                                                    }
                                                }
                                                li("page-item"){
                                                    a("page-link"){
                                                        attrs {
                                                            href = "#"
                                                        }
                                                        +"2"
                                                    }
                                                }
                                                li("page-item"){
                                                    a("page-link"){
                                                        attrs {
                                                            href = "#"
                                                        }
                                                        +"3"
                                                    }
                                                }
                                                li("page-item disabled"){
                                                    a("page-link"){
                                                        attrs{
                                                            href="#"
                                                            attributes["aria-label"] ="Next"
                                                        }
                                                        span {
                                                            attrs{
                                                                attributes["aria-hidden"]="true"
                                                            }
                                                            +"»"
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
