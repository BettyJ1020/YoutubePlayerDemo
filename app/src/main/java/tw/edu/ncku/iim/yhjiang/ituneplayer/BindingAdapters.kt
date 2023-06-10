package tw.edu.ncku.iim.yhjiang.ituneplayer

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SongEntry>?) {
    val adapter = recyclerView.adapter as? iTuneRecyclerViewAdapter
    if (adapter != null && data != null) {
        adapter.songs = data
    }


}