package apps.utility.reactTable

import kotlinext.js.Object
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import apps.utility.reactModalDialog.modalReactJsPerson
import org.w3c.dom.HTMLSelectElement
import react.*
import react.dom.*
import react.table.*



external interface TableReactJsProps : RProps {
    var data : Array<Person>
    var columns : Array< out Column<Person,*>>
    var setPersons : (Array<Person>) -> Unit
}


fun RBuilder.tableReactJs(handler: TableReactJsProps.() -> Unit) = child(tableReactJs) {
    attrs {
        handler()
    }
}

private val tableReactJs =  functionalComponent<TableReactJsProps> { props ->
    console.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB ${props.data.size}")

    val (persons, setPersons) = useState(props.data)
    val (show , setShow) = useState(false)

    useEffect(listOf(props.data)){
        setPersons(props.data)
    }

    val colhere = useMemo(emptyList<dynamic>()) {
        props.columns
    }


    val onEditRowClick = useCallback { person: Person ->
        console.info("EDIT")
        setShow(true)
    }

    val onDeleteRowClick =  { person: Person ->
        console.info("DELETE")
        console.info("DELETE PROP ${persons.size}")
        console.info("PPPPPPPPP ${person.firstName} ${person.lastName}")
        val aa = persons.toList().filter { !((it.firstName == person.firstName) && (it.lastName == person.lastName)) }
        console.info("DELETE AA ${aa.size}")
        console.info("DELETE PROP ${persons.size}")
        setPersons(aa.toTypedArray())
    }

    val table = useTable<Person>( useSortBy,usePagination,useRowSelect) {
        this.data = persons
        this.columns = colhere
    }

    /*useEffect(emptyList()) {
        setPersons(props.data.toTypedArray())
        //table.setPageSize(10)
        //table.gotoPage(pageIndex)
    }*/

    console.info("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCC")

    console.info("${table.pageOptions} ${table.pageCount}")



    div {
        modalReactJsPerson{
            this.showM = show
            this.onCloseM = setShow
        }
        div("card shadow") {
            div("card-header py-3") {
                p("text-primary m-0 font-weight-bold") {
                    +"Users"
                }
            }
            div("card-body") {
                div("row") {
                    div("col-md-6 text-nowrap") {
                        div("dataTables_length") {
                            attrs {
                                id = "dataTable_length"
                                attributes["aria-controls"] = "dataTable"
                            }
                            label {
                                +"Show "
                                select("form-control form-control-sm custom-select custom-select-sm") {
                                    attrs {
                                        value = table.state.pageSize.toString()
                                        onChangeFunction = { event ->
                                            val target = event.target as HTMLSelectElement
                                            table.setPageSize(target.value.toInt())
                                        }
                                    }
                                    option() {
                                        attrs.value = "10"
                                        attrs.selected = true
                                        +"10"
                                    }
                                    option() {
                                        attrs.value = "25"
                                        +"25"
                                    }
                                    option() {
                                        attrs.value = "50"
                                        +"50"
                                    }
                                    option() {
                                        attrs.value = "75"
                                        +"75"
                                    }
                                    option() {
                                        attrs.value = "100"
                                        +"100"
                                    }
                                }
                            }
                        }
                    }
                    div("col-md-6") {
                        div("text-md-right dataTables_filter") {
                            attrs { id = "dataTable_filter" }
                            label {
                                input(type = InputType.search, classes = "form-control form-control-sm") {
                                    attrs {
                                        placeholder = "Search"
                                        attributes["aria-controls"] = "dataTable"
                                    }
                                }
                            }
                        }
                    }
                }
                div("table-responsive table mt-2") {
                    attrs {
                        id = "dataTable"
                        role = "grid"
                    }

                    table(classes = "table my-0") {
                        val tprops = table.getTableProps()
                        attrs {
                            attributes["extraAttrs"] = "dataTable"
                            for (key in Object.keys(tprops)) {
                                @Suppress("UnsafeCastFromDynamic")
                                attributes[key] = tprops.asDynamic()[key]
                            }
                        }
                        thead {
                            for (headerGroup in table.headerGroups) {
                                tr {
                                    val hGroups = headerGroup.getHeaderGroupProps()
                                    attrs {
                                        for (key in Object.keys(hGroups)) {
                                            @Suppress("UnsafeCastFromDynamic")
                                            attributes[key] = hGroups.asDynamic()[key]
                                        }
                                    }
                                    for (h in headerGroup.headers) {
                                        val originalHeader = h.placeholderOf
                                        val header = originalHeader ?: h
                                        val hProps = header.getHeaderProps(header.getSortByToggleProps())
                                        th {
                                            attrs {
                                                for (key in Object.keys(hProps)) {
                                                    @Suppress("UnsafeCastFromDynamic")
                                                    attributes[key] = hProps.asDynamic()[key]
                                                }
                                                //header.getHeaderProps()
                                            }
                                            +header.render(RenderType.Header)
                                            span {
                                                if (header.isSorted) if (header.isSortedDesc) +" 🔽" else +" 🔼" else +""
                                            }

                                        }
                                    }
                                    th {
                                        +"Action"
                                    }
                                }
                            }
                        }
                        tbody {
                            val tBProps = table.getTableBodyProps()
                            attrs {
                                for (key in Object.keys(tBProps)) {
                                    @Suppress("UnsafeCastFromDynamic")
                                    attributes[key] = tBProps.asDynamic()[key]
                                }
                            }
                            for (row in table.page) {
                                table.prepareRow(row)
                                val rProps = row.getRowProps()
                                tr {
                                    attrs {
                                        for (key in Object.keys(rProps)) {
                                            @Suppress("UnsafeCastFromDynamic")
                                            attributes[key] = rProps.asDynamic()[key]
                                        }
                                    }

                                    for (cell in row.cells) {
                                        val cProps = cell.getCellProps()
                                        td {
                                            attrs {
                                                for (key in Object.keys(cProps)) {
                                                    @Suppress("UnsafeCastFromDynamic")
                                                    attributes[key] = cProps.asDynamic()[key]
                                                }
                                            }
                                            +cell.render(RenderType.Cell)
                                        }
                                    }

                                    td {
                                        div("row") {
                                            div("col-md-6") {
                                                button(classes = "btn btn-default btn-lg") {
                                                    attrs {
                                                        onClickFunction = { onEditRowClick(row.original) }
                                                    }
                                                    span(classes = "material-icons md-dark") {
                                                        +"mode_edit"
                                                    }
                                                    +"Edit"
                                                }
                                            }
                                            div("col-md-6") {
                                                button(classes = "btn btn-default btn-lg") {
                                                    attrs {
                                                        onClickFunction = { onDeleteRowClick(row.original) }
                                                    }
                                                    span(classes = "material-icons md-dark") {
                                                        +"delete"
                                                    }
                                                    +"Delete"
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                div("row") {
                    div("col-md-6 align-self-center") {
                        p("dataTables_info") {
                            attrs {
                                id = "dataTable_info"
                                role = "status"
                                attributes["aria-live"] = "polite"
                            }
                            +"Showing ${table.state.pageIndex + 1} of ${table.pageOptions.size}"
                        }
                    }
                    div("col-md-6") {
                        div("pagination") {
                            button(classes = "btn btn-outline-primary", type = ButtonType.button) {
                                attrs {
                                    disabled = !table.canPreviousPage
                                    onClickFunction = {
                                        table.gotoPage(0)
                                    }
                                }
                                +"<<"
                            }
                            button(classes = "btn btn-outline-primary", type = ButtonType.button) {
                                attrs {
                                    disabled = !table.canPreviousPage
                                    onClickFunction = {
                                        table.previousPage()
                                    }
                                }
                                +"<"
                            }
                            button(classes = "btn btn-outline-primary", type = ButtonType.button) {
                                attrs {
                                    disabled = !table.canNextPage
                                    onClickFunction = {
                                        table.nextPage()
                                    }
                                }
                                +">"
                            }
                            button(classes = "btn btn-outline-primary", type = ButtonType.button) {
                                attrs {
                                    disabled = !table.canNextPage
                                    onClickFunction = {
                                        table.gotoPage(table.pageCount - 1)
                                    }
                                }
                                +">>"
                            }
                        }
                    }
                }
            }
        }
    }
}



