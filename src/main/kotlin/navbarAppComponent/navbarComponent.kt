package navbarAppComponent

import kotlinx.browser.document
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import kotlinx.html.ButtonType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import model.AppRoute
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLElement
import react.RBuilder
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent
import react.router.dom.routeLink


external interface NavBarProps : RProps {
    var title: String
    var url : String
    var listRoute : List<AppRoute>
}

external interface ListLinkProps : RProps {
    var url : String
    var listRoute : List<AppRoute>
}

external interface ItemLinkProps : RProps {
    var url : String
    var itemRoute : AppRoute
}

fun RBuilder.navbar(handler: NavBarProps.() -> Unit) = child(navbar) {
    attrs {
        handler()
    }
}

private val navbar =  functionalComponent<NavBarProps> { props ->
    nav("navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0"){
        attrs {id = "navbarR"}
        div("container-fluid d-flex flex-column p-0"){
            a(href = "#", classes = "navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0"){
                div("sidebar-brand-icon rotate-n-15"){
                    i( "fas fa-laugh-wink"){}

                }
                div ("sidebar-brand-text mx-3"){
                    span{
                        +props.title
                    }
                }
            }
            hr("sidebar-divider my-0"){}
            child(listlink){
                attrs.listRoute = props.listRoute
                attrs.url = props.url
            }
            div ("text-center d-none d-md-inline"){
                button( classes="btn rounded-circle border-0" ,type=ButtonType.button) {
                    attrs {
                        id="sidebarToggle"
                        onClickFunction = {
                            val bodyel = document.getElementById("page-top") as HTMLBodyElement
                            val navbarR = document.getElementById("navbarR") as HTMLElement
                            if (bodyel.classList.length == 0) {
                                bodyel.addClass("sidebar-toggled")
                                navbarR.addClass("toggled")
                            }
                            else {
                                bodyel.removeClass("sidebar-toggled")
                                navbarR.removeClass("toggled")
                            }

                        }
                    }

                }
            }
        }
    }
}



private val listlink =  functionalComponent<ListLinkProps> { props ->
    ul ("nav navbar-nav text-light") {
        attrs { id = "accordionSidebar" }
        props.listRoute.forEach {
            child(itemlink){
                attrs.itemRoute = it
                attrs.url = props.url
            }
        }
    }
}

private val itemlink =  functionalComponent<ItemLinkProps> { props ->
    li ("nav-item") {
        if (props.itemRoute.active) {
            routeLink("${props.url}/${props.itemRoute.url}", className = "nav-link") {
                i(props.itemRoute.classesLink) {}
                span { +props.itemRoute.descriptionLink }
            }
        } else {
            routeLink("${props.url}/${props.itemRoute.url}", className = "nav-link") {
                i(props.itemRoute.classesLink) {}
                span { +props.itemRoute.descriptionLink }
            }
        }

       /* a(props.itemRoute.url,classes= "nav-link" ) {
            i (props.itemRoute.classesLink){}
            span{ +props.itemRoute.descriptionLink}

        }*/
    }
}




/*
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
                    <div class="sidebar-brand-text mx-3">
                        <span>ROBY</span>
                    </div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.html">
                            <i class="fas fa-tachometer-alt"></i><span>Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="ConfigScriptRun.html"><i class="fas fa-user"></i><span>Profile</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="table.html"><i class="fas fa-table"></i><span>Table</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="login.html"><i class="far fa-user-circle"></i><span>Login</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="register.html"><i class="fas fa-user-circle"></i><span>Register</span></a></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>

 */