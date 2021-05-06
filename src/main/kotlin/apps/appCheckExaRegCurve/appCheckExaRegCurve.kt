package apps.appCheckExaRegCurve

import apps.appCheckExaRegCurve.model.InfoChartCheckRegCurve
import headerfotterAppComponent.fotterapp
import headerfotterAppComponent.headerapp
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.html.id
import model.RespondItem
import org.w3c.fetch.RequestInit
import react.*
import react.dom.*
import kotlin.js.json

external interface AppCheckExaRegCurveProps : RProps {
    var title: String
}

fun RBuilder.appCheckExaRegCurve(handler: AppCheckExaRegCurveProps.() -> Unit) = child(appCheckExaRegCurve) {
    attrs {
        handler()
    }
}


suspend fun fetchInfo() : RespondItem<InfoChartCheckRegCurve> {
    return try {
        console.log("GET INFO ")
        val response  = window.fetch(UrlRestApi.urlRegCurveChartInfo, object: RequestInit {
            override var method: String? = "GET"
            //override var body: dynamic = user.getJson()//JSON.stringify(user)
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()

        val info = response.json().await().unsafeCast<RespondItem<InfoChartCheckRegCurve>>()
        if (response.ok) info else RespondItem("KO", response.statusText, InfoChartCheckRegCurve.newEmptyRegCurveChartInfo())
    } catch(e:Throwable){
        console.log(e.message.toString())
        RespondItem("KO", "SERVER PROBLEMS", InfoChartCheckRegCurve.newEmptyRegCurveChartInfo())
    }

}



private val appCheckExaRegCurve =  functionalComponent<AppCheckExaRegCurveProps> { props ->
    val (infoChart, setInfoChart) = useState(InfoChartCheckRegCurve.newEmptyRegCurveChartInfo())
    val (awaitInfo,setAwaiInfo) = useState(true)
    val (errorInput, setErrorInput) = useState("")
    useEffect(emptyList()) {
        val mainScope = MainScope()
        mainScope.launch {
            //delay(5000)
            try {
                val respinfo = fetchInfo()
                setAwaiInfo(false)
                console.log("GET INFO RESULT " + respinfo.result)
                if (respinfo.result == "KO") {
                    setErrorInput(respinfo.message)
                } else {
                    setInfoChart(respinfo.item)
                }
            } catch (e: Throwable) {
                console.log("ERROR INFO CHART " + e.message.toString())
                setAwaiInfo(false)
                setErrorInput( e.message.toString())
            }
        }
    }


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
                            +props.title
                        }
                        div("card shadow") {
                            div("card-header py-3") {
                                p("text-primary m-0 font-weight-bold") {
                                    +"Select Value"
                                }
                            }
                            div("card-body") {
                                div("row"){
                                    div("col-md-6 text-nowrap"){
                                        label {
                                            +"Pick your favorite flavor:"
                                            select("form-control form-control-sm custom-select custom-select-sm") {
                                                //attrs.value = state.value
                                                //attrs.onChangeFunction = {handleChange(it)}

                                                option {
                                                    attrs.value = "grapefruit"
                                                    +"grapefruit"
                                                }
                                                option {
                                                    attrs.value = "lime"
                                                    +"lime"
                                                }
                                                option {
                                                    attrs.value = "coconut"
                                                    +"coconut"
                                                }
                                                option {
                                                    attrs.value = "mango"
                                                    +"mango"
                                                }
                                            }
                                        }
                                    }
                                    div("col-md-6"){

                                    }
                                }

                            }
                        }
                        div("card shadow") {
                            div("card-header py-3") {
                                p("text-primary m-0 font-weight-bold") {
                                    +"CHART"
                                }
                            }
                            div("card-body") {
                                div("row"){
                                    div("col-md-6 text-nowrap"){

                                    }
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
