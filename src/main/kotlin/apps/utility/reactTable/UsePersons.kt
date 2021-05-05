package apps.utility.reactTable

import kotlinx.coroutines.*
import react.RCleanup
import react.rawUseEffect
import react.useState


@DelicateCoroutinesApi
fun usePersons(): Array<Person> {
    var data by useState(emptyArray<Person>())

    useEffectWithCleanupP {
        val job = GlobalScope.launch {
            data = getPersons()
        }
        job::cancel
    }

    return data
}

private suspend fun getPersons(): Array<Person> = makeData(30).toTypedArray()

fun useEffectWithCleanupP(
    vararg dependencies: dynamic,
    callback: () -> RCleanup,
) = rawUseEffect(callback, dependencies)
