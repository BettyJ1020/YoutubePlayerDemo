package tw.edu.ncku.iim.yhjiang.ituneplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tw.edu.ncku.iim.yhjiang.ituneplayer.databinding.ActivitySwipeRefreshBinding

class MainActivity : AppCompatActivity(){
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragment) as YoutubeListFragment?
//         listFragment?.listener = object: YoutubeListFragment.onVideoSelectedListener {
//             override fun onVideoSelected(title: String, description: String, video: String) {
//                 val previewFragment = supportFragmentManager.findFragmentById(R.id.previewFragment) as YoutubePreviewFragment?
//                 previewFragment?.previewVideo(title, description, video)
//             }
//         }
    }

//    override fun onVideoSelected(position: Int) {
//        val previewFragment = supportFragmentManager.findFragmentById(R.id.previewFragment) as YoutubePreviewFragment?
//        if (previewFragment != null) { // on tablet
//            previewFragment?.previewVideo(title, description, video)
//        } else { // on phone
//            val action = YoutubeListFragmentDirections.actionYoutubeListFragmentToYoutubePreviewFragment(
//                title, description, video)
//
//            findNavController(R.id.navHostFragment).navigate(action)
//        }
//    }


}