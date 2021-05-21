package apps.appAnalisiTisDp

import apps.appAnalisiTisDp.model.AnalysisYearMonthRifTisDp
import apps.utility.reactChart.*
import react.*
import react.dom.div
import react.dom.label

external interface ChartAnalisiTisDpProps : RProps {
    var typeChart : String
    var yearMonthRif : Int
    var voltageTis : String
    var towardsEnergyTis : String
    var data : Array<AnalysisYearMonthRifTisDp>
}

fun RBuilder.chartAnalisiTisDp(handler: ChartAnalisiTisDpProps.() -> Unit) = child(chartAnalisiTisDp) {
    attrs {
        handler()
    }
}

private val chartAnalisiTisDp =  functionalComponent<ChartAnalisiTisDpProps>{ props ->




    div {
        console.log("IN CHART")
        console.log(props.typeChart)
        console.log(props.data)
        console.log("1 IN CHART")


        div("row") {
            console.log("3 IN CHART")
            if (props.yearMonthRif > 0) {
                label { +"TYPE: ${props.typeChart} YEARMONTHRIF: ${props.yearMonthRif}  VOLTAGE: ${props.voltageTis} TOWARDSENERGY: ${props.towardsEnergyTis}" }
            } else {
                label { +"TYPE: ${props.typeChart}  VOLTAGE: ${props.voltageTis} TOWARDSENERGY: ${props.towardsEnergyTis}" }
            }
            ResponsiveContainer {
                attrs.width = 600
                attrs.height = 400
                LineChart {
                    attrs.data = props.data
                    attrs.width = 500
                    attrs.height = 300
                    attrs.margin = Margin(5, 30, 50, 5)
                    CartesianGrid { attrs.strokeDashArray = "3 3" }
                    XAxis {
                        attrs.dataKey = "dayAnalysis"
                        attrs.angle = 90.0
                        attrs.dy = 20
                    }
                    YAxis {
                        attrs.hide = true
                        //attrs.type = "number"
                        //attrs.domain = arrayOf("0",(props.data).filter { item -> item.stato == stato }.map { it.nr }.maxOf { it }.toString() )
                    }
                    Tooltip {}
                    Legend {
                        attrs.verticalAlign = "top"
                        attrs.height = 36
                    }
                    Line {
                        attrs.name = "activeF1"
                        attrs.type = "monotone"
                        attrs.dataKey = "activeF1"
                        attrs.stroke = "#2f2fdd"
                        attrs.activeDot = "{ r: 8 }"
                    }
                    Line {
                        attrs.name = "activeF2"
                        attrs.type = "monotone"
                        attrs.dataKey = "activeF2"
                        attrs.stroke = "#22ea45"
                        attrs.activeDot = "{ r: 8 }"
                    }
                    Line {
                        attrs.name = "activeF3"
                        attrs.type = "monotone"
                        attrs.dataKey = "activeF3"
                        attrs.stroke = "#e01fd1"
                        attrs.activeDot = "{ r: 8 }"
                    }
                    Line {
                        attrs.name = "activeTot"
                        attrs.type = "monotone"
                        attrs.dataKey = "activeTot"
                        attrs.stroke = "#c8c864"
                        attrs.activeDot = "{ r: 8 }"
                    }
                }
            }

            ResponsiveContainer {
                attrs.width = 600
                attrs.height = 400
                LineChart {
                    attrs.data = props.data
                    attrs.width = 500
                    attrs.height = 300
                    attrs.margin = Margin(5, 30, 50, 5)
                    CartesianGrid { attrs.strokeDashArray = "3 3" }
                    XAxis {
                        attrs.dataKey = "dayAnalysis"
                        attrs.angle = 90.0
                        attrs.dy = 20
                    }
                    YAxis {
                        attrs.hide = true
                        //attrs.type = "number"
                        //attrs.domain = arrayOf("0",(props.data).filter { item -> item.stato == stato }.map { it.nr }.maxOf { it }.toString() )
                    }
                    Tooltip {}
                    Legend {
                        attrs.verticalAlign = "top"
                        attrs.height = 36
                    }
                    Line {
                        attrs.name = "nr"
                        attrs.type = "monotone"
                        attrs.dataKey = "nr"
                        attrs.stroke = "#2f2fdd"
                        attrs.activeDot = "{ r: 8 }"
                    }
                }
            }
        }

    }
}