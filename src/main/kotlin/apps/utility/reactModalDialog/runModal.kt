package apps.utility.reactModalDialog

import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.button
import react.dom.div
import react.dom.h1
import react.dom.span
import react.functionalComponent
import react.useCallback
import react.useState

val runModal =  functionalComponent<RProps>{
    val (show , setShow) = useState(false)

    val onEditRowClick = useCallback {
        console.info("EDIT")
        setShow(true)
    }

    div{
        div("col-md-6") {
            button(classes = "btn btn-default btn-lg") {
                attrs {
                    onClickFunction = { onEditRowClick() }
                    attributes["data-toggle"] = "modal"
                    attributes["data-target"] = "#staticBackdrop"

                }
                span(classes = "material-icons md-dark") {
                    +"mode_edit"
                }
                +"Edit"
            }
        }

        h1{
            +"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        }
        modalReactJsPerson{
            this.showM = show
            this.onCloseM = setShow
        }
    }
}


