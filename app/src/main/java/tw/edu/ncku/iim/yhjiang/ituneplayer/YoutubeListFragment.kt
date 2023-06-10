package tw.edu.ncku.iim.yhjiang.ituneplayer

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import tw.edu.ncku.iim.yhjiang.ituneplayer.databinding.FragmentYoutubeListBinding


class YoutubeListFragment : Fragment(), iTuneRecyclerViewAdapter.RecyclerViewClickListener{

    private val model: SharedViewModel by activityViewModels()
//    interface onVideoSelectedListener {
//        fun onVideoSelected(position: Int)
//    }
//
//    var listener: onVideoSelectedListener? = null // hold the reference to the callback listener


    lateinit var binding: FragmentYoutubeListBinding

//    override fun onAttach(context: Context) { // context 即 activity
//        super.onAttach(context)
//        listener = context as? onVideoSelectedListener // 強制 host activity implement
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = activity // let binding to be observer of live data
        binding.model = model

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )


        model.loadList()
    }



    val adapter: iTuneRecyclerViewAdapter by lazy {
        iTuneRecyclerViewAdapter(listOf<SongEntry>(), this)
    }

    val swipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
    }




    override fun onItemClick(view: View, position: Int) {
//        val title = adapter.songs[position].title
//        val description = adapter.songs[position].description
//        val video = adapter.songs[position].video

//        listener?.onVideoSelected(position)
        model.selectVideo(position)

        if (parentFragment != null) {
            val action = YoutubeListFragmentDirections.actionYoutubeListFragmentToYoutubePreviewFragment()
            findNavController().navigate(action)
        }
    }
}