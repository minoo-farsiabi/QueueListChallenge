import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class QueueListTest {

    private val queueList = QueueList()

    @Test
    fun getTracks_TrueWhenEmptyTracks() {
        assert(queueList.tracks!!.isEmpty())
    }

    @Test
    fun getTracks_CorrectSizeWhenMultipleTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        assertEquals(queueList.tracks!!.size, 2)
    }

    @Test
    fun getCurrentTrack_ThrowsExceptionWhenNoTrack() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Empty Tracks!",
            block = {
                queueList.currentTrack()
            }
        )
    }

    @Test
    fun getCurrentTrack_CorrectWhenHasTrack() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        assertEquals(queueList.currentTrack().id, "916409")
    }

    @Test
    fun next_ThrowsExceptionWhenEmptyTracks() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Empty Tracks!",
            block = {
                queueList.next()
            }
        )
    }

    @Test
    fun next_CorrectWhenMultipleTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        assertEquals(queueList.next().id, "916412")
    }

    @Test
    fun next_CorrectWhenRestartTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        queueList.next()
        assertEquals(queueList.next().id, "916409")
    }

    @Test
    fun previous_ThrowsExceptionWhenEmptyTracks() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Empty Tracks!",
            block = {
                queueList.previous()
            }
        )
    }

    @Test
    fun previous_CorrectWhenMultipleTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        queueList.next()
        assertEquals(queueList.previous().id, "916409")
    }

    @Test
    fun previous_CorrectWhenRestartTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )

        assertEquals(queueList.next().id, "916412")
    }

}