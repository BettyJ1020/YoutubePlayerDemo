package tw.edu.ncku.iim.yhjiang.ituneplayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val videoList = MutableLiveData<List<SongEntry>>() // notify when changing
    val isRefreshing = MutableLiveData<Boolean>() // 載入的圈圈

    val selectedVideo = MutableLiveData<SongEntry>()

    fun selectVideo(index: Int) {
        videoList.value?. let { // if not null
            if (index >= 0 && index < it.size) { // 不超過 videoList的範圍
                selectedVideo.value = it[index]
            }

        }
    }



    fun loadList() {
        videoList.value?.let { // 避免 configuration change時重載video list
            if (it.size > 0) return
        }
        iTuneXMLParser(object: ParserListener{
            override fun start() {
                isRefreshing.value = true
            }

            override fun finish(songs: List<SongEntry>) {
                videoList.value = songs
                isRefreshing.value = false
            }

        }).parseURL(
            "https://www.youtube.com/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw")

    }

    fun reloadList() { // empty data to reload
        videoList.value = listOf<SongEntry>()
        loadList()
    }

}