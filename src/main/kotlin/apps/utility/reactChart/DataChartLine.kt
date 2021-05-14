package apps.utility.reactChart

import org.w3c.dom.events.Event
import react.RProps

data class Data(val name : String, val uv : Double, val pv : Double, val amt : Double)

data class Margin(val top : Int, val right : Int, val bottom : Int, val left : Int)
data class Padding(val left : Int, val right : Int)
data class Tick(val stroke : String, val strokeWidth: Int)
data class Payload(val value : String, val type : String, val id: String)
data class Points(val x : Double, val y : Double, val value : Double)

external interface ResponsiveContainerProps : RProps {
    //var aspect: Int  //optional
    var width : dynamic //DEFAULT: '100%'
    var height : dynamic //DEFAULT: '100%'
    //var minWidth : Int //optional
    //var minHeight : Int //optional
    //var debounce : Int //default 0
}

external interface LineChartProps :  RProps {
    //var layout : String // default 'horizontal' | 'vertical'
    //var syncId : String //optional
    var width : Int
    var height : Int
    var data : Array<dynamic>
    var margin : Margin
    //var onClick : (Event) -> Unit // optinal
    //var onMouseEnter : (Event) -> Unit // optinal
    //var onMouseMove : (Event) -> Unit // optinal
    //var onMouseLeave : (Event) -> Unit // optinal
}

external interface CartesianGridProps :  RProps {
    /*
    ar x : Double //default 0
    var y : Double //default 0
    var width : Int //default 0
    var height : Int //default 0
    var horizontal : Boolean // default true
    var vertical : Boolean // default true
    var horizontalPoints : Array<Double> // default empty
    var verticalPoints : Array<Double> // default empty
    */
    var strokeDashArray : String //"4 1"
}

external interface XAxisProps :  RProps {
    //var hide : Boolean //default false
    var dataKey : String
    var angle : Double
    var dy : Int
    /*
    var xAxisId : String // default 0
    var width : Int //default 0
    var height : Int // default 30
    var orientation : String // default 'bottom' , 'top'
    var type : String // 'number' | default 'category'
    var allowDecimals : Boolean // default true
    var allowDataOverflow : Boolean // default false
    var allowDuplicatedCategory : Boolean //default true
    var angle : Double //default 0
    var tickCount : Int //default 5
    var domain : Array<String> // DEFAULT: [0, 'auto']
    var interval : String // "preserveStart" | default "preserveEnd" | "preserveStartEnd" | Number
    var padding : Padding
    var minTickGap : Int // DEFAULT: 5
    var axisLine : Boolean //DEFAULT: true
    var tickLine : Boolean //DEFAULT: true
    var tickSize : Int // DEFAULT: 6
    var tickFormatter : (String) -> Unit
    var ticks : Array<Any>
    var tick : Tick
    var mirror : Boolean //DEFAULT: false
    var reversed : Boolean //DEFAULT: false
    var label : String
    var scale : String //default 'auto' | 'linear' | 'pow' | 'sqrt' | 'log' | 'identity' | 'time' | 'band' | 'point' | 'ordinal' | 'quantile' | 'quantize' | 'utc' | 'sequential' | 'threshold'
    var unit : String //optional
    var name : String //optional
    var onClick : (Event)-> Unit //optional
    var onMouseDown  : (Event)-> Unit //optional
    var onMouseUp : (Event)-> Unit //optional
    var onMouseMove : (Event)-> Unit //optional
    var onMouseOver : (Event)-> Unit //optional
    var onMouseOut : (Event)-> Unit //optional
    var onMouseEnter : (Event)-> Unit //optional
    var onMouseLeave : (Event)-> Unit //optional
    var tickMargin : Int //optional

     */
}

external interface YAxisProps :  RProps {
    var hide : Boolean //default false
    var dataKey : String
    var yAxisId : String // default 0
    var width : Int //default 0
    var height : Int // default 30
    var orientation : String // default 'left' | 'right'
    var type : String // defuaol 'number' | 'category'
    var tickCount : Int //default 5
    var domain : Array<String> // DEFAULT: [0, 'auto']
    var interval : String // "preserveStart" | default "preserveEnd" | "preserveStartEnd" | Number
    var padding : Padding
    var minTickGap : Int // DEFAULT: 5
    var allowDecimals : Boolean //DEFAULT: true
    var allowDataOverflow : Boolean //DEFAULT: false
    var allowDuplicatedCategory : Boolean //DEFAULT: true
    var axisLine : Boolean //DEFAULT: true
    var tickLine : Boolean //DEFAULT: true
    var tickSize : Int // DEFAULT: 6
    var tickFormatter : (String) -> Unit
    var ticks : Array<Any>
    var tick : Tick
    var mirror : Boolean //DEFAULT: false
    var reversed : Boolean //DEFAULT: false
    var label : String
    var scale : String //default 'auto' | 'linear' | 'pow' | 'sqrt' | 'log' | 'identity' | 'time' | 'band' | 'point' | 'ordinal' | 'quantile' | 'quantize' | 'utc' | 'sequential' | 'threshold'
    var unit : String //optional
    var name : String //optional
    var onClick : (Event)-> Unit //optional
    var onMouseDown  : (Event)-> Unit //optional
    var onMouseUp : (Event)-> Unit //optional
    var onMouseMove : (Event)-> Unit //optional
    var onMouseOver : (Event)-> Unit //optional
    var onMouseOut : (Event)-> Unit //optional
    var onMouseEnter : (Event)-> Unit //optional
    var onMouseLeave : (Event)-> Unit //optional
    var tickMargin : Int //optional
}

external interface LegendProps :  RProps {
    var verticalAlign : String //'top',default  'middle', 'bottom'
    var height: Int //optional
    /*
    var width: Int //optional
    var height: Int //optional
    var layout : String // default 'horizontal', 'vertical'
    var align : String // default  'center', 'left', 'right'
    var verticalAlign : String //'top',default  'middle', 'bottom'
    var iconSize : Int //DEFAULT: 14
    var iconType : String //'line' | 'plainline' | 'square' | 'rect' | 'circle' | 'cross' | 'diamond' | 'star' | 'triangle' | 'wye'
    var payload : Array<Payload>  //DEFAULT: []  example: [{ value: 'item name', type: 'line', id: 'ID01' }]
    var chartWidth : Int
    var chartHeight : Int
    var margin : Margin
    var content : ReactElement // FORMAT: <Legend content={<CustomizedLegend external={external} />} />
    var onMouseDown  : (Event)-> Unit //optional
    var onMouseUp : (Event)-> Unit //optional
    var onMouseMove : (Event)-> Unit //optional
    var onMouseOver : (Event)-> Unit //optional
    var onMouseOut : (Event)-> Unit //optional
    var onMouseEnter : (Event)-> Unit //optional
    var onMouseLeave : (Event)-> Unit //optional

     */
}

external interface LineProps :  RProps {
    var name : String
    var type : String // default linear 'basis' | 'basisClosed' | 'basisOpen' | 'linear' | 'linearClosed' | 'natural' | 'monotoneX' | 'monotoneY' | 'monotone' | 'step' | 'stepBefore' | 'stepAfter'
    var dataKey : String
    var stroke : String
    var activeDot : String
    /*
    var xAxisIdS: Int  //DEFAULT: 0
    var yAxisId : Int  //DEFAULT: 0
    var legendType : String // default 'line' | 'square' | 'rect'| 'circle' | 'cross' | 'diamond' | 'square' | 'star' | 'triangle' | 'wye' | 'none'optional
    var dot : String  //DEFAULT: true
    var activeDot : Boolean //DEFAULT: true
    var label : Boolean  // default false
    var points : Array<Points>
    var strokeWidth : Int //optional default 1
    var layout : String // 'horizontal' | 'vertical'optional
    var connectNulls : Boolean //DEFAULT: false
    var unit : String
    var name : String
    var isAnimationActive : Boolean //DEFAULT: true in CSR, and false in SSR
    var animationBegin : Int //DEFAULT: 0
    var animationDuration : Int //DEFAULT: 1500
    var animationEasing : String // default 'ease' | 'ease-in' | 'ease-out' | 'ease-in-out' | 'linear'
    var idString : String //optional
    var onClick : (Event)-> Unit //optional
    var onMouseDown  : (Event)-> Unit //optional
    var onMouseUp : (Event)-> Unit //optional
    var onMouseMove : (Event)-> Unit //optional
    var onMouseOver : (Event)-> Unit //optional
    var onMouseOut : (Event)-> Unit //optional
    var onMouseEnter : (Event)-> Unit //optional
    var onMouseLeave : (Event)-> Unit //optional
    var strokeDashArray : String //strokeDashArray="4 1"

     */
}