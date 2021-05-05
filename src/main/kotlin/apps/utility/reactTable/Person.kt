package apps.utility.reactTable

import kotlin.random.Random



data class Person(val firstName: String,
                  val lastName :String,
                  val age : Int,
                  val visits : Int,
                  val progress : Int,
                  val status : Double
)

fun makeData(lens : Int) : List<Person> {
    return range (lens).map {
        newPerson ()
    }
}

fun newPerson () : Person {
    val statusChance = Random.nextInt(0, 100)
    return Person("pippo${statusChance}",
        "rr${statusChance}",
        statusChance,
        2*statusChance,
        3*statusChance,
        statusChance/3.4)
}

fun range (len : Int) : List<Int>{
    val arr = mutableListOf<Int>()
    for( i in 0 .. len) {
        arr.add(i)
    }
    return arr
}


