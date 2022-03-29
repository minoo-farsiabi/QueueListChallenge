package element

import kotlinx.serialization.Serializable

@Serializable
class Album {
    val tracks: List<Track?> = listOf()
}