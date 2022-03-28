class Track(
    id: String? = null,
    artist: String? = null,
    title: String? = null,
    duration: Int? = null,
    media: Media? = null
) {
    var id: String
    val artist: String
    val title: String
    val duration: Int
    val media: Media

    init {
        this.id = id!!
        this.artist = artist!!
        this.title = title!!
        this.duration = duration!!
        this.media = media!!
    }
}