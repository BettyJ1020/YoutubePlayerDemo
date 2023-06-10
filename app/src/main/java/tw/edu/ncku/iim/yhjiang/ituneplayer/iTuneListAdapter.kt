package tw.edu.ncku.iim.yhjiang.ituneplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class iTuneListAdapter(val context: Context): BaseAdapter() {
    var songs = listOf<SongEntry>()
    set(value) {
        field = value // value = song
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return songs.size
    }

    override fun getItem(p0: Int): Any {
        return songs[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    // 將資料轉為View
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.itune_list_item, null)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        textView.text = songs[p0].title
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        imageView.setImageBitmap(songs[p0].cover)

        return itemView
    }
}