import element.Media
import element.Track
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class QueueListTest {

    private val queueList = QueueList()

    @Test
    fun getTracks_TrueWhenEmptyTracks() {
        assert(queueList.tracks!!.isEmpty())
    }

    @Test
    fun getTracks_TrueSizeWhenMultipleTracks() {
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
    fun getCurrentTrack_TrueWhenHasTrack() {
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
    fun next_TrueWhenMultipleTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        assertEquals(queueList.next().id, "916412")
    }

    @Test
    fun next_TrueWhenRestartTracks() {
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
    fun previous_TrueWhenMultipleTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )
        queueList.next()
        assertEquals(queueList.previous().id, "916409")
    }

    @Test
    fun previous_TrueWhenRestartTracks() {
        queueList.tracks = mutableListOf(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )

        assertEquals(queueList.next().id, "916412")
    }

    @Test
    fun add_TrueWhenOneInput() {
        queueList.add(Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")))

        assertEquals(queueList.tracks!!.size, 1)
    }

    @Test
    fun add_TrueWhenMultipleInputs() {
        queueList.add(
            Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")),
            Track("916412", "Eminem", "White America", 324, Media("916412"))
        )

        assertEquals(queueList.tracks!!.size, 2)
    }

    @Test
    fun add_FailsWhenNullInputExists() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Invalid Input!",
            block = {
                queueList.add(Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")), null)
            }
        )
    }

    @Test
    fun removeAt_TrueWhenItemFoundAndIsNotCurrentTrack() {
        val firstTrack = Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409"))
        val secondTrack = Track("916412", "Eminem", "White America", 324, Media("916412"))
        queueList.add(firstTrack, secondTrack)

        queueList.removeAt(secondTrack)

        assertEquals(queueList.tracks!!.size, 1)
        assertEquals(queueList.currentTrack().id, "916409")
    }

    @Test
    fun removeAt_TrueWhenItemFoundAndIsCurrentTrack() {
        val firstTrack = Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409"))
        val secondTrack = Track("916412", "Eminem", "White America", 324, Media("916412"))
        queueList.add(firstTrack, secondTrack)

        queueList.removeAt(firstTrack)

        assertEquals(queueList.tracks!!.size, 1)
        assertEquals(
            queueList.currentTrack().id,
            "916412"
        ) //We could also use assertNotEquals but 'assertEquals' is more specific
    }

    @Test
    fun removeAt_FailsWhenNullInput() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Invalid Input!",
            block = {
                queueList.tracks = mutableListOf(Track("916412", "Eminem", "White America", 324, Media("916412")))
                queueList.removeAt(null)
            }
        )
    }

    @Test
    fun removeAt_FailsWhenEmptyList() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Empty Tracks!",
            block = {
                queueList.removeAt(Track("916412", "Eminem", "White America", 324, Media("916412")))
            }
        )
    }

    @Test
    fun removeAt_FailsWhenTrackNotFound() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Track Not Found!",
            block = {
                queueList.tracks = mutableListOf(Track("916412", "Eminem", "White America", 324, Media("916412")))
                queueList.removeAt(Track("916409", "Eminem", "Curtains Up (Skit)", 29, Media("916409")))
            }
        )
    }

    @Test
    fun fillQueueFromJSON_TrueWhenSuccessfulReadFromFile() {
        queueList.fillQueueFromJSON("data/tracks.json")

        assertEquals(queueList.tracks!!.size, 20)
    }

    @Test
    fun fillQueueFromJSON_FailsWhenFileNotExists() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "File Not Found!",
            block = {
                queueList.fillQueueFromJSON("data/trackssss.json")
            }
        )
    }

    @Test
    fun fillQueueFromJSON_FailsWhenEmptyFile() {
        assertFailsWith(
            exceptionClass = Exception::class,
            message = "Reading Failed!",
            block = {
                val pathDir: Path?
                var path: Path? = null
                try {
                    pathDir = Files.createTempDirectory(Paths.get("../"), "tempDir")
                    path = Files.createTempFile(pathDir!!, "temp", ".json")
                    queueList.fillQueueFromJSON(path.toString())
                } finally {
                    path!!.toFile().deleteOnExit()
                }
            }
        )
    }

}