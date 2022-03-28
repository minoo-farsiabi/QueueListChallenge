class QueueList {
    var tracks: List<Track>? = mutableListOf()

    private var currentIndex: Int

    init {
        currentIndex = 0
    }

    fun currentTrack(): Track {
        if (isEmptyQueue) throw Exception("Empty Tracks!")
        return tracks!![currentIndex]
    }

    fun next(): Track {
        if (isEmptyQueue) throw Exception("Empty Tracks!")
        currentIndex = (currentIndex + 1) % tracks!!.size
        return tracks!![currentIndex]
    }

    fun previous(): Track {
        if (isEmptyQueue) throw Exception("Empty Tracks!")
        if (currentIndex == 0) {
            currentIndex = tracks!!.size
        }
        currentIndex = (currentIndex - 1) % tracks!!.size
        return tracks!![currentIndex]
    }

    private val isEmptyQueue: Boolean
        get() = tracks == null || tracks!!.isEmpty()
}