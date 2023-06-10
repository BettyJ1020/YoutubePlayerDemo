package tw.edu.ncku.iim.yhjiang.ituneplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.edu.ncku.iim.yhjiang.ituneplayer.databinding.ItuneListItemBinding

class iTuneRecyclerViewAdapter(
    data: List<SongEntry>,
    val listener: RecyclerViewClickListener):
    RecyclerView.Adapter<iTuneRecyclerViewAdapter.ViewHolder>()  {

    // to save data
    var songs = listOf<SongEntry>()
    set(value) { // value = song
        field = value
        notifyDataSetChanged()
    }

    interface RecyclerViewClickListener {
        fun onItemClick(view: View, position: Int)
    }
    class ViewHolder(val binding: ItuneListItemBinding): RecyclerView.ViewHolder(binding.root) {
//        val textView = view.findViewById<TextView>(R.id.textView)
//        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItuneListItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.songEntry = songs[position]
        holder.binding.root.setOnClickListener {
            listener.onItemClick(it, position)
        }
    }

}