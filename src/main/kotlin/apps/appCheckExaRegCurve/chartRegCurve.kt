package apps.appCheckExaRegCurve

import apps.appCheckExaRegCurve.model.RegCurvaChart
import apps.utility.reactChart.*
import react.*
import react.dom.div
import react.dom.label

external interface ChartRegCurveProps : RProps {
    var typeChart : String
    var statoChart : String
    var data : Array<RegCurvaChart>
}

fun RBuilder.chartRegCurvee(handler: ChartRegCurveProps.() -> Unit) = child(chartRegCurve) {
    attrs {
        handler()
    }
}

private val chartRegCurve =  functionalComponent<ChartRegCurveProps>{ props ->
    div {
        console.log("IN CHART")
        console.log(props.typeChart)
        console.log(props.statoChart)
        console.log(props.data)

            console.log("1 IN CHART")
            if (props.statoChart == "ALL") {
                console.log("2 IN CHART")
                props.data.map { it.stato}.distinct().forEach { stato ->
                    div("row") {
                        console.log("3 IN CHART")
                        label { +stato }
                        ResponsiveContainer {
                            attrs.width = 600
                            attrs.height = 400
                            console.log("4 IN CHART $stato")
                            LineChart {
                                attrs.data = (props.data).filter { item -> item.stato == stato }.toTypedArray()
                                attrs.width = 500
                                attrs.height = 300
                                attrs.margin = Margin(5, 30, 50, 5)
                                CartesianGrid { attrs.strokeDashArray = "3 3" }
                                XAxis {
                                    attrs.dataKey = "gg_analisi"
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
                                    attrs.name = "nr $stato"
                                    attrs.type = "monotone"
                                    attrs.dataKey = "nr"
                                    attrs.stroke = "#8884d8"
                                    attrs.activeDot = "{ r: 8 }"
                                }
                            }
                        }
                    }
                }

            } else {
                div("row") {
                    label { +props.statoChart }
                    ResponsiveContainer {
                        attrs.width = 600
                        attrs.height = 400
                        LineChart {
                            attrs.data = (props.data).filter { item -> item.stato == props.statoChart }.toTypedArray()
                            attrs.width = 500
                            attrs.height = 300
                            attrs.margin = Margin(5, 30, 50, 5)
                            CartesianGrid { attrs.strokeDashArray = "3 3" }
                            XAxis {
                                attrs.dataKey = "gg_analisi"
                                attrs.angle = 90.0
                                attrs.dy = 20
                            }
                            YAxis {
                                attrs.hide = true
                                //attrs.type = "number"
                                //attrs.domain = arrayOf("0",(props.data).filter { item -> item.stato == props.statoChart }.map { it.nr }.maxOf { it }.toString() )
                            }
                            Tooltip {}
                            Legend {
                                attrs.verticalAlign = "top"
                                attrs.height = 36
                            }
                            Line {
                                attrs.name = "nr ${props.statoChart}"
                                attrs.type = "monotone"
                                attrs.dataKey = "nr"
                                attrs.stroke = "#8884d8"
                                attrs.activeDot = "{ r: 8 }"
                            }
                        }
                    }
                }
            }

    }
}