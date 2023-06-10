package tw.edu.ncku.iim.yhjiang.ituneplayer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.net.URL

class iTuneXMLParser(val listener: ParserListener) {
    private val factory = XmlPullParserFactory.newInstance()
    private val parser = factory.newPullParser() // get access to parse doc.

    private var text = "" // save tag string
    private var songTitle = ""
    private var descriptionText = ""
    private var videoId = ""
    private val data = mutableListOf<SongEntry>() // add/del SongEntry object

    private var imageFound = false

    @OptIn(DelicateCoroutinesApi::class)
    fun parseURL(url: String) {

        var songCover: Bitmap? = null
        listener.start()

        // GlobalScope: 只要application存在，coroutine就會持續運行
        // launch(Dispatchers.IO): 將coroutine置於專門處理IO的thread 執行，optional to add
        GlobalScope.launch {
            try {
                val inputStream = URL(url).openStream()
                parser.setInput(inputStream, null) // read XML doc.
                var eventType = parser.next()

                // get song title
                while (eventType != XmlPullParser.END_DOCUMENT) {// not END of XML doc.
                    var tagName = parser.name

                    // read <entry>
                    if (tagName.equals("entry", ignoreCase = true) && eventType == XmlPullParser.START_TAG) {
                        // while not </entry>
                        while (!(tagName.equals("entry", ignoreCase = true) && eventType == XmlPullParser.END_TAG)) {
                            when(eventType) { // when is similar to 'switch'
                                XmlPullParser.START_TAG -> if(tagName.equals("media:thumbnail", ignoreCase = true)) {
                                    imageFound = parser.getAttributeValue(null, "height").equals("360")
                                }
                                XmlPullParser.TEXT -> text = parser.text

                                // if read </tag>, save to variables
                                XmlPullParser.END_TAG -> if (tagName.equals("title", ignoreCase = true)) {
                                    songTitle = text
                                } else if (tagName.equals("media:thumbnail", ignoreCase = true) && imageFound) {
                                    val url = parser.getAttributeValue(null, "url")
                                    imageFound = false
                                    val inputStream = URL(url).openStream()
                                    songCover = BitmapFactory.decodeStream(inputStream)
                                    imageFound = false
                                } else if (tagName.equals("media:description", ignoreCase = true)) {
                                    descriptionText = text
                                } else if (tagName.equals("yt:videoId", ignoreCase = true)) {
                                    videoId = text
                                }

                            }
                            eventType = parser.next()
                            tagName = parser.name
                        }
                        data.add(SongEntry(songTitle, songCover, descriptionText, videoId))

                    }
                    eventType = parser.next()

                }
                // 由 background 切回 UI
                withContext(Dispatchers.Main) {
                    listener.finish(data)
                }
            } catch (e: Throwable) {
                e.printStackTrace() // print err msg
            }
        }
    }
}