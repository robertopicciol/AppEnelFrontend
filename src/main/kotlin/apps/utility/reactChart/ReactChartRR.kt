@file:JsModule("recharts")
@file:JsNonModule

package apps.utility.reactChart

import react.*

@JsName("ResponsiveContainer")
external val ResponsiveContainer: RClass<ResponsiveContainerProps>



@JsName("LineChart")
external val LineChart: RClass<LineChartProps>

@JsName("CartesianGrid")
external val CartesianGrid: RClass<CartesianGridProps>

@JsName("XAxis")
external val XAxis: RClass<XAxisProps>

@JsName("YAxis")
external val YAxis: RClass<YAxisProps>

@JsName("Tooltip")
external val Tooltip: RClass<RProps>

@JsName("Legend")
external val Legend : RClass<LegendProps>

@JsName("Line")
external val Line : RClass<LineProps>