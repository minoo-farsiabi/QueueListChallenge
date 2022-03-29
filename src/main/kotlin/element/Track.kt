package element

import kotlinx.serialization.*

@Serializable
class Track(
    val id: String? = null,
    val artist: String? = null,
    val title: String? = null,
    val duration: Int? = null,
    val media: Media? = null
)