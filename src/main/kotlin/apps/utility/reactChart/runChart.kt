package apps.utility.reactChart

import react.RProps
import react.dom.div
import react.functionalComponent
import react.useState



val data = arrayOf(
    Data( "Page A",4000.0,2400.0, 2400.0),
    Data( "Page B",3000.0, 1398.0, 2210.0),
    Data( "Page C",2000.0,9800.0,2290.0),
    Data( "Page D", 2780.0,3908.0,2000.0),
    Data( "Page E",1890.0, 4800.0,2181.0),
    Data( "Page F",2390.0,3800.0,2500.0),
    Data( "Page G",3490.0,4300.0,2100.0))



val runChart =  functionalComponent<RProps>{
    val (dataC, SetDataC) = useState(data)
    div {
        ResponsiveContainer{
            attrs.width= 600
            attrs.height=400
            LineChart{
                attrs.data = dataC
                attrs.width = 500
                attrs.height= 300
                attrs.margin = Margin(5,30,50,5)
                CartesianGrid{ attrs.strokeDashArray = "3 3"}
                XAxis { attrs.dataKey = "name"
                        attrs.angle= 90.0
                        attrs.dy = 20
                }
                YAxis {}
                Tooltip {}
                Legend{
                    attrs.verticalAlign="top"
                    attrs.height=36
                }
                Line {
                    attrs.name="pv of pages"
                    attrs.type="monotone"
                    attrs.dataKey="pv"
                    attrs.stroke = "#8884d8"
                    attrs.activeDot = "{ r: 8 }"
                }
                Line {
                    attrs.type="monotone"
                    attrs.dataKey="uv"
                    attrs.stroke = "#82ca9d"
                    attrs.activeDot = "{ r: 8 }"
                }
            }
        }
   }
}
/*
<ResponsiveContainer width="100%" height="100%">
<LineChart
width={500}
height={300}
data={data}
margin={{
        top: 5,
        right: 30,
        left: 20,
        bottom: 5,
}}
>
<CartesianGrid strokeDasharray="3 3" />
<XAxis dataKey="name" />
<YAxis />
<Tooltip />
<Legend />
<Line type="monotone" dataKey="pv" stroke="#8884d8" activeDot={{ r: 8 }} />
<Line type="monotone" dataKey="uv" stroke="#82ca9d" />
</LineChart>
</ResponsiveContainer>
);
*/