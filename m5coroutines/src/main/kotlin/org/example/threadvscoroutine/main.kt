import kotlinx.coroutines.runBlocking
import org.example.threadvscoroutine.runCoroutine
import org.example.threadvscoroutine.runThread

fun main() {
//    runThread()
    runBlocking {
        runCoroutine()
        runCoroutine()
        runCoroutine()
    }
}
