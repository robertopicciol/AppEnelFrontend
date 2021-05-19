package apps.appAnalisiTisDp

import apps.appAnalisiTisDp.model.AnalysisTisDp
import apps.appAnalisiTisDp.model.AnalysisTisDpYearPost
import apps.appAnalisiTisDp.model.AnalysisTisDpInfo
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


external interface AppAnalysisTisDpProps : RProps {
    var title: String
}

fun RBuilder.appAnalysisTisDp(handler: AppAnalysisTisDpProps.() -> Unit) = child(appAnalysisTisDp) {
    attrs {
        handler()
    }
}

suspend fun fetchInfo() : RespondItem<Array<AnalysisTisDpInfo>> {
    try {
        console.log("GET INFO ")
        val response  = window.fetch(UrlRestApi.urlAnalysisTisDpChartInfo, object: RequestInit {
            override var method: String? = "GET"
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()
        val info = response.json().await().unsafeCast<RespondItem<Array<AnalysisTisDpInfo>>>()
        console.log(info)
        return if (response.ok){
            info
        } else RespondItem("KO", response.statusText, emptyArray())
    } catch(e:Throwable){
        console.log(e.message.toString())
        return RespondItem("KO", "SERVER PROBLEMS", emptyArray())
    }

}


suspend fun fetchData(type : String, yearMonthAnalysis : Int) : RespondItem<AnalysisTisDp> {
    try {
        console.log("GET INFO ")
        console.log(AnalysisTisDpYearPost(type,yearMonthAnalysis).getJson())
        val response  = window.fetch(UrlRestApi.urlAnalysisTisDpChartData, object: RequestInit {
            override var method: String? = "POST"
            override var body: dynamic = AnalysisTisDpYearPost(type,yearMonthAnalysis).getJson()//JSON.stringify(user)
            override var credentials = "same-origin".asDynamic()
            override var headers: dynamic = json("Content-Type" to "application/json") //json("Accept" to "application/json","Content-Type" to "application/json")
        }).await()
        val info = response.json().await().unsafeCast<RespondItem<AnalysisTisDp>>()
        console.log(info)
        return if (response.ok){
            info
        } else RespondItem("KO", response.statusText, AnalysisTisDp.newEmptyAnalisiTisDp(type,yearMonthAnalysis))
    } catch(e:Throwable){
        console.log(e.message.toString())
        return RespondItem("KO", "SERVER PROBLEMS", AnalysisTisDp.newEmptyAnalisiTisDp(type,yearMonthAnalysis))
    }
}


private val appAnalysisTisDp =  functionalComponent<AppAnalysisTisDpProps> { props ->
    val (infoCharts, setInfoCharts) = useState(emptyArray<AnalysisTisDpInfo>())
    val (infoChart, setInfoChart) = useState(AnalysisTisDpInfo.newEmptyAnalisiTisDpInfo())
    val (awaitInfo,setAwaitInfo) = useState(true)
    val (errorInputInfo, setErrorInputInfo) = useState("")
    val (type,setType) = useState("")
    val (yearMonthAnalysisChart, setYearMonthAnalysisChart) = useState(0)
    val (voltageTisChart, setVoltageTisChart) = useState("ERR")
    val (towardsEnergyTisChart,setTowardsEnergyTisChart) = useState("ERR")
    val (yearTisChart, setYearTisChart) = useState(0)
    val (yearMonthArrayTisDp, setYearMonthArrayTisDp) = useState(emptyArray<Int>())
    val (yearMonthTisChart, setYearMonthTisChart) = useState(0)


    val (awaitData,setAwaitData) = useState(true)
    val (errorInputData, setErrorInputData) = useState("")
    val (dataChart, SetDataChart) = useState( AnalysisTisDp.newEmptyAnalisiTisDp(null,null))

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
                        console.log(respinfo.item)
                        setInfoChart(respinfo.item.first())
                        console.log(respinfo.item.first())
                        setType(respinfo.item.first().type ?: "ERR")
                        setYearMonthAnalysisChart(respinfo.item.first().yearMonthAnalysis.sortedArrayDescending().first())
                        setYearTisChart(respinfo.item.first().yearRif.first())
                        console.log(respinfo.item.first().yearRif.first())
                        console.log(respinfo.item.first())

                        val yearMonths = (respinfo.item.first().yearRif.first() * 100 + 1 .. respinfo.item.first().yearRif.first() * 100 + 12)
                            .filter { it <= respinfo.item.first().maxYearMonthRif ?: 0 }.toTypedArray()

                        setYearMonthArrayTisDp(yearMonths)
                        setYearMonthTisChart(yearMonths.first())
                        setVoltageTisChart(respinfo.item.first().voltage.first())
                        setTowardsEnergyTisChart(respinfo.item.first().towardsEnergy.first())
                        console.log("DATIIIIII")
                        try {
                            val respData = fetchData(
                                respinfo.item.first().type ?: "ERR",
                                respinfo.item.first().yearMonthAnalysis.sortedArrayDescending().first()
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
        setInfoChart(info)
        setAwaitData(true)
        mainScope.launch {
            try {
                val respData = fetchData(info.type ?: "ERR", infoChart.yearMonthAnalysis.first())
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

    val handleChangeYearMonthAnalysis = { event: Event ->
        val mainScope = MainScope()
        val target = event.target as HTMLSelectElement
        val yearMonth = target.value.unsafeCast<Int>()
        setYearMonthAnalysisChart(yearMonth)

        setAwaitData(true)
        mainScope.launch {
            try {
                val respData = fetchData(type, yearMonth)
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
                                            div("col-md-3 text-nowrap") {
                                                div("row") {
                                                    label {
                                                        +"Type: "
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

                                            div("col-md-3 text-nowrap"){
                                                div("row") {
                                                    label {
                                                        +"Month Analysis: "
                                                        select("form-control form-control-sm custom-select custom-select-sm") {
                                                            attrs.id = "selectYearMonthAnalysis"
                                                            attrs.value = yearMonthAnalysisChart.toString()
                                                            attrs.onChangeFunction = { event ->
                                                                handleChangeYearMonthAnalysis(event)
                                                            }

                                                            infoChart.yearMonthAnalysis.forEach { yearMonth ->
                                                                option {
                                                                    attrs.value = yearMonth.toString()
                                                                    +yearMonth.toString()
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                            div("col-md-3 text-nowrap"){
                                                div("row") {
                                                    label {
                                                        +"Voltage: "
                                                        select("form-control form-control-sm custom-select custom-select-sm") {
                                                            attrs.id = "selectVoltageTisChart"
                                                            attrs.value = voltageTisChart
                                                            attrs.onChangeFunction = { event ->
                                                                val target = event.target as HTMLSelectElement
                                                                setVoltageTisChart(target.value)
                                                            }

                                                            infoChart.voltage.forEach { voltage ->
                                                                option {
                                                                    attrs.value = voltage
                                                                    +voltage
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                            div("col-md-3 text-nowrap"){
                                                div("row") {
                                                    label {
                                                        +"Towards Energy: "
                                                        select("form-control form-control-sm custom-select custom-select-sm") {
                                                            attrs.id = "selectTowardsEnergyTisChart"
                                                            attrs.value = towardsEnergyTisChart
                                                            attrs.onChangeFunction = { event ->
                                                                val target = event.target as HTMLSelectElement
                                                                setTowardsEnergyTisChart(target.value)
                                                            }

                                                            infoChart.towardsEnergy.forEach { towardsEnergy ->
                                                                option {
                                                                    attrs.value = towardsEnergy
                                                                    +towardsEnergy
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                    }
                                }

                                div("card shadow") {
                                    div("card-header py-3") {
                                        div("row") {
                                            div("col-md-6 text-nowrap") {
                                                div("row") {
                                                    label {
                                                        +"Year Tis: "
                                                        select("form-control form-control-sm custom-select custom-select-sm") {
                                                            attrs.id = "selectYearTisChart"
                                                            attrs.value = yearTisChart.toString()
                                                            attrs.onChangeFunction = { event ->
                                                                val target = event.target as HTMLSelectElement
                                                                val year = target.value.unsafeCast<Int>()
                                                                setYearTisChart(target.value.unsafeCast<Int>())
                                                                val yearMonths = (year * 100 + 1 .. year * 100 + 12)
                                                                    .filter { it <= infoChart.maxYearMonthRif ?: 0 }.toTypedArray()
                                                                setYearMonthArrayTisDp(yearMonths)
                                                                setYearMonthTisChart(yearMonths.first())
                                                            }

                                                            infoChart.yearRif.forEach { year ->
                                                                option {
                                                                    attrs.value = year.toString()
                                                                    +year.toString()
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div("col-md-6 text-nowrap") {
                                                 div("row") {
                                                     label {
                                                         +"Year Month Tis: "
                                                         select("form-control form-control-sm custom-select custom-select-sm") {
                                                             attrs.id = "selectYearMonthTisChart"
                                                             attrs.value = yearMonthTisChart.toString()
                                                             attrs.onChangeFunction = { event ->
                                                                 val target = event.target as HTMLSelectElement
                                                                 setYearMonthTisChart(target.value.unsafeCast<Int>())
                                                             }

                                                             yearMonthArrayTisDp.forEach { monthRif ->
                                                                 option {
                                                                     attrs.value = monthRif.toString()
                                                                     +monthRif.toString()
                                                                 }
                                                             }
                                                         }
                                                     }
                                                 }
                                             }
                                        }
                                    }
                                    /*div("card-body") {
                                        div("row") {
                                            div("col-md-12 text-nowrap") {
                                                chartRegCurvee{
                                                    typeChart = type
                                                    statoChart = stateChart
                                                    data = dataChart
                                                }
                                            }

                                        }

                                    }*/
                                }


                                /*

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
                                }*/
                            }
                        }

                    }
                }
            }
        }
        fotterapp()
    }
}
