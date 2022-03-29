fun main(args: Array<String>) {
    println("Hello Deezer World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val queueList = QueueList()
//    val firstTrack = Track("916409", "Eminem", "Curtains Up (Skit)", 29, element.Media("916409"))
//    queueList.add(firstTrack, firstTrack)

//    queueList.removeAt(firstTrack)

    queueList.fillQueueFromJSON("data/tracks.json")

//    println(queueList.tracks!!.size)

    for (i in 0..90) {
        println(i.toString() + " curr:" + queueList.currentTrack().title)
        queueList.next()
    }

    println("*************")

    for (i in 0..90) {
        println(i.toString() + " curr:" + queueList.currentTrack().title)
        queueList.previous()
    }
}