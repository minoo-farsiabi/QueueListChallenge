class QueueList {
    var tracks: MutableList<Track>? = mutableListOf()

    private var currentIndex: Int

    init {
        currentIndex = 0
    }

    fun currentTrack(): Track {
        if (isEmptyQueue)
            throw Exception("Empty Tracks!")
        return tracks!![currentIndex]
    }

    fun next(): Track {
        if (isEmptyQueue)
            throw Exception("Empty Tracks!")
        currentIndex = (currentIndex + 1) % tracks!!.size
        return tracks!![currentIndex]
    }

    fun previous(): Track {
        if (isEmptyQueue)
            throw Exception("Empty Tracks!")
        if (currentIndex == 0) {
            currentIndex = tracks!!.size
        }
        currentIndex = (currentIndex - 1) % tracks!!.size
        return tracks!![currentIndex]
    }

    fun add(vararg items: Track?) {
        for (track in items) {
            if (track == null)
                throw Exception("Invalid Input!")
        }

        try {
            for (track in items) {
                tracks!!.add(track!!)
            }
        } catch (e: Throwable) {
            System.err.println(e.message)
            throw Exception("Error While Adding Track")
        }
    }

    fun removeAt(track: Track?) {
        if (track == null)
            throw Exception("Invalid Input!")

        try {
            if (tracks!!.isEmpty())
                throw Exception("Empty Tracks!")

            val index = tracks!!.indexOf(track)
            if (index == -1)
                throw Exception("Track Not Found!")

            tracks!!.remove(track)
            if (index == currentIndex) {
                next()
            }
        } catch (e: Throwable) {
            System.err.println(e.message)
            throw Exception("Error While Removing Track" + e.message)
        }
    }

    private val isEmptyQueue: Boolean
        get() = tracks == null || tracks!!.isEmpty()
}