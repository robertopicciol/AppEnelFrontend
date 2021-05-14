package apps.appCheckExaRegCurve

import apps.appCheckExaRegCurve.model.CheckRegCurvePost
import apps.appCheckExaRegCurve.model.InfoChartCheckRegCurve
import apps.appCheckExaRegCurve.model.RegCurvaChart
import apps.utility.spinnerComponent
import headerfotterAppComponent.fotterapp
import headerfotterAppComponent.headerapp
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.role
import model.RespondItem
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
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


suspend fun fetchInfo() : RespondItem<Array<InfoChartCheckRegCurve>> {
    try {
        console.log("GET INFO ")
        val response  = window.fetch(UrlRestApi.urlRegCurveChartInfo, object: RequestInit {
            override var method: String? = "GET"
            //override var body: dynamic = user.getJson()//JSON.stringify(user)
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()
        val info = response.json().await().unsafeCast<RespondItem<Array<InfoChartCheckRegCurve>>>()
        console.log(info)
        return if (response.ok){
            info
        } else RespondItem("KO", response.statusText, emptyArray())
    } catch(e:Throwable){
        console.log(e.message.toString())
        return RespondItem("KO", "SERVER PROBLEMS", emptyArray())
    }

}

suspend fun fetchData(type : String, annomese : Long) : RespondItem<Array<RegCurvaChart>> {
    try {
        console.log("GET INFO ")
        console.log(CheckRegCurvePost(type,annomese).getJson())
        val response  = window.fetch(UrlRestApi.urlRegCurveChartData, object: RequestInit {
            override var method: String? = "POST"
            override var body: dynamic = CheckRegCurvePost(type,annomese).getJson()//JSON.stringify(user)
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()
        val info = response.json().await().unsafeCast<RespondItem<Array<RegCurvaChart>>>()
        console.log(info)
        return if (response.ok){
            info
        } else RespondItem("KO", response.statusText, emptyArray())
    } catch(e:Throwable){
        console.log(e.message.toString())
        return RespondItem("KO", "SERVER PROBLEMS", emptyArray())
    }
}

private val appCheckExaRegCurve =  functionalComponent<AppCheckExaRegCurveProps> { props ->
    val (infoCharts, setInfoCharts) = useState(emptyArray<InfoChartCheckRegCurve>())
    val (infoChart, setInfoChart) = useState(InfoChartCheckRegCurve.newEmptyRegCurveChartInfo())
    val (awaitInfo,setAwaitInfo) = useState(true)
    val (errorInputInfo, setErrorInputInfo) = useState("")
    val (type,setType) = useState("")
    val (stateChart, setStateChart) = useState("")
    val (yearMonthInsChart, setYearMonthInsChart) = useState(0L)

    val (awaitData,setAwaitData) = useState(true)
    val (errorInputData, setErrorInputData) = useState("")
    val (dataChart, SetDataChart) = useState(emptyArray<RegCurvaChart>())

    useEffect(emptyList()) {
        val mainScope = MainScope()
        mainScope.launch {
            try {
                val respinfo = fetchInfo()
                if (respinfo.result == "KO") {
                    setErrorInputInfo(respinfo.message)
                } else {
                    if (respinfo.item.isNotEmpty()) {
                        setInfoCharts(respinfo.item)
                        setInfoChart(respinfo.item.first())
                        setType(respinfo.item.first().type ?: "ERR")
                        setStateChart("ALL")
                        setYearMonthInsChart(respinfo.item.first().maxAnnomeseIns ?: 0L)
                        try {
                            val respData = fetchData(
                                respinfo.item.first().type ?: "ERR",
                                respinfo.item.first().maxAnnomeseIns ?: 0L
                            )
                            console.log("DATIIIIII")
                            console.log(respData)
                            console.log(respData.message)
                            if (respData.result == "KO") {
                                setErrorInputData(respData.message)
                            } else {
                                SetDataChart(respData.item)
                            }
                        } catch (e: Throwable) {
                            console.log("ERROR DATA CHART " + e.message.toString())
                            setErrorInputData( e.message.toString())
                        }
                        setAwaitData(false)
                    } else {
                        setErrorInputInfo( "Empty Info Charts")
                    }
                }
            } catch (e: Throwable) {
                console.log("ERROR INFO CHART " + e.message.toString())
                setErrorInputInfo( e.message.toString())

            }
            setAwaitInfo(false)
        }

    }

    val handleChangeType = { event: Event ->
        val mainScope = MainScope()
        val target = event.target as HTMLSelectElement
        val info = infoCharts.first { info -> info.type == target.value }
        setType(info.type ?: "ERR")
        setStateChart("ALL")
        setYearMonthInsChart(info.maxAnnomeseIns ?: 0L)
        setInfoChart(info)
        setAwaitData(true)
        mainScope.launch {
            try {
                val respData = fetchData(info.type ?: "ERR", info.maxAnnomeseIns ?: 0L)
                console.log("DATIIIIII")
                console.log(respData)
                if (respData.result == "KO"){
                    setErrorInputData(respData.message)
                } else {
                    SetDataChart(respData.item)
                }
                setAwaitData(false)
            } catch (e: Throwable) {
                console.log("ERROR DATA CHART " + e.message.toString())
                setErrorInputData( e.message.toString())

            }
        }

    }

    val handleChangeAnniMeseIns = { event: Event ->
        val mainScope = MainScope()
        val target = event.target as HTMLSelectElement
        val annomese = target.value.unsafeCast<Long>()
        setYearMonthInsChart(annomese)

        setAwaitData(true)
        mainScope.launch {
            try {
                val respData = fetchData(type, annomese)
                if (respData.result == "KO"){
                    setErrorInputData(respData.message)
                } else {
                    SetDataChart(respData.item)
                }
                setAwaitData(false)
            } catch (e: Throwable) {
                console.log("ERROR DATA CHART " + e.message.toString())
                setErrorInputData( e.message.toString())

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

                        if (awaitInfo) {
                            div("col-md-6 text-nowrap") {
                                spinnerComponent()
                            }
                        } else {
                            if (errorInputInfo != "") {
                                div(classes = "alert alert-danger") {
                                    attrs.role = "alert"
                                    +errorInputInfo
                                }
                            } else {
                                div("card shadow") {
                                    div("card-header py-3") {
                                        p("text-primary m-0 font-weight-bold") {
                                            +"Select Value"
                                        }
                                    }
                                    div("card-body") {
                                        div("row"){
                                            div("col-md-4 text-nowrap") {
                                                div("row") {
                                                    label {
                                                        +"Type:  "
                                                        select("form-control form-control-sm custom-select custom-select-sm") {
                                                            attrs.id = "selectType"
                                                            attrs.disabled = awaitInfo
                                                            attrs.value = type
                                                            attrs.onChangeFunction = { event ->
                                                                handleChangeType(event)
                                                            }

                                                            infoCharts.forEach { info ->
                                                                option {
                                                                    val itype = info.type ?: "ERR"
                                                                    attrs.value = itype
                                                                    +itype
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                           div("col-md-4 text-nowrap"){
                                               div("row") {
                                                   label {
                                                       +"State:  "
                                                       select("form-control form-control-sm custom-select custom-select-sm") {
                                                           attrs.id = "selectState"
                                                           attrs.value = stateChart
                                                           attrs.onChangeFunction = { event ->
                                                               val target = event.target as HTMLSelectElement
                                                               setStateChart(target.value)
                                                           }

                                                           option {
                                                               attrs.value = "ALL"
                                                               +"ALL"
                                                           }
                                                           infoChart.stati.forEach { stato ->
                                                               option {
                                                                   attrs.value = stato
                                                                   +stato
                                                               }
                                                           }
                                                       }
                                                   }
                                               }

                                            }

                                            div("col-md-4 text-nowrap"){
                                                div("row") {
                                                    label {
                                                        +"Date Ins:  "
                                                        select("form-control form-control-sm custom-select custom-select-sm") {
                                                            attrs.id = "selectYearMonthIns"
                                                            attrs.value = yearMonthInsChart.toString()
                                                            attrs.onChangeFunction = { event ->
                                                                handleChangeAnniMeseIns(event)
                                                            }
                                                            infoChart.yearMonth.forEach { yearMonth ->
                                                                option {
                                                                    attrs.value = yearMonth.toString()
                                                                    +yearMonth.toString()
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                    }
                                }
                                if (awaitData){
                                    div("col-md-6 text-nowrap") {
                                        spinnerComponent()
                                    }
                                } else {
                                    if (errorInputData != "") {
                                        console.log("ERRORE DATA")
                                        div(classes = "alert alert-danger") {
                                            attrs.role = "alert"
                                            +errorInputData
                                        }
                                    } else {
                                        if (dataChart.isEmpty()) {
                                            console.log("EMPTY DATA")
                                            div(classes = "alert alert-danger") {
                                                attrs.role = "alert"
                                                +"Nessun Dato per le scete fatte"
                                            }
                                        } else {
                                            console.log("OK DATA")
                                            console.log(dataChart)
                                            console.log(type)
                                            console.log(stateChart)
                                            div("card shadow") {
                                                div("card-header py-3") {
                                                    p("text-primary m-0 font-weight-bold") {
                                                        +"CHART"
                                                    }
                                                }
                                                div("card-body") {
                                                    div("row") {
                                                        div("col-md-12 text-nowrap") {
                                                            chartRegCurvee{
                                                                typeChart = type
                                                                statoChart = stateChart
                                                                data = dataChart
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
        }
        fotterapp()
    }
}
