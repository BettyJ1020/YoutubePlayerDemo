package tw.edu.ncku.iim.yhjiang.ituneplayer

interface ParserListener { // show song title on the screen
    fun start()
    fun finish(songs: List<SongEntry>)
}