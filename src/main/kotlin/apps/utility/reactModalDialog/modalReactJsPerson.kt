package apps.utility.reactModalDialog

import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*


external interface ModalReactJsPersonProps : RProps {
    var showM : Boolean
    var onCloseM : (Boolean)-> Unit
}

fun RBuilder.modalReactJsPerson(handler: ModalReactJsPersonProps.() -> Unit) = child(modalReactJsPerson) {
    attrs {
        handler()
    }
}

private val modalReactJsPerson =  functionalComponent<ModalReactJsPersonProps> { props ->
    console.info("EDIT AAAAAAAAAAAAAAAAA ${props.showM}")

    val closeM = useCallback {
        props.onCloseM(false)
    }

    if (props.showM) {
        console.info("EDIT BBBBBBB AAAAAAAAAAAAAAAAA ${props.showM}")
        div(classes = "modal fade") {
            attrs.role = "dialog"
            attrs.tabIndex = "-1"
            attrs.attributes["data-bs-backdrop"] = "static"
            attrs.attributes["data-bs-keyboard"] = "false"
            attrs.attributes["aria-labelledby"] = "staticBackdropLabel"
            attrs.attributes["aria-hidden"] = "true"
            attrs.id="staticBackdrop"
            div(classes = "modal-dialog") {
                attrs.role = "document"
                div(classes="modal-content") {
                    div(classes = "modal-header") {
                        h5(classes = "modal-title") {
                            +"New message"
                        }
                        button(classes = "close", type = ButtonType.button) {
                            attrs.onClickFunction = { closeM() }
                            attrs.attributes["data-dismiss"]="modal"
                            span {
                                +"X"
                            }
                        }
                    }
                    div(classes = "modal-body") {
                        form {
                            div(classes = "form-group") {
                                label(classes = "col-form-label") {
                                    +"FFFFFF"
                                }
                                input(classes = "form-control", type = InputType.text) {
                                    attrs.id = "recipient-name"
                                }
                            }
                            div(classes = "form-group") {
                                label(classes = "col-form-label") {
                                   +"DDDDD"
                                }
                                input(classes = "form-control", type = InputType.text) {
                                    attrs.id = "message-text"
                                }
                            }
                        }
                    }
                    div(classes = "modal-footer") {
                        button(classes = "btn btn-secondary", type = ButtonType.button) {
                            attrs.onClickFunction = { closeM() }
                            attrs.attributes["data-dismiss"]="modal"
                            +"Close"
                        }
                        button(classes = "btn btn-secondary", type = ButtonType.button) {
                            +"Save"
                        }
                    }
                }
            }
        }
    }
}


/*
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Recipient:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Send message</button>
      </div>
    </div>
  </div>
</div>
 */