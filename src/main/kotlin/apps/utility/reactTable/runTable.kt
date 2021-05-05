package apps.utility.reactTable

import react.RProps
import react.functionalComponent
import react.table.columns
import react.useEffect
import react.useState

val runPP =  functionalComponent<RProps>{
    val (persons, setPersons) = useState<Array<Person>>(emptyArray())
    useEffect(emptyList()) {
        setPersons(makeData(100).toTypedArray())
        //table.setPageSize(10)
        //table.gotoPage(pageIndex)
    }
    val cols =  columns<Person> {
        column<String> {
            header = "firstName"
            accessorFunction = { it.firstName }

        }
        column<String> {
            header = "lastname"
            accessorFunction = { it.lastName }
        }
        column<Int> {
            header = "age"
            accessorFunction = { it.age }
        }
        column<Int> {
            header = "visits"
            accessorFunction = { it.visits }
        }
        column<Int> {
            header = "progress"
            accessorFunction = { it.progress }
        }
        column<Double> {
            header = "status"
            accessorFunction = { it.status }
        }
    }

    console.info("CCCCCCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ${persons.size}")

    tableReactJs{
        this.columns = cols
        this.data = persons
        this.setPersons = setPersons
    }
}

