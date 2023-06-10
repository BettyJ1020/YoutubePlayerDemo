package tw.edu.ncku.iim.yhjiang.ituneplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import tw.edu.ncku.iim.yhjiang.ituneplayer.databinding.FragmentYoutubeListBinding
import tw.edu.ncku.iim.yhjiang.ituneplayer.databinding.FragmentYoutubePreviewBinding
import com.google.android.youtube.player.YouTubePlayerSupportFragment

class YoutubePreviewFragment: Fragment() {
    val YOUTUBE_API_KEY = "AIzaSyAG6M2Q1ix-OfyZ7FfV-4a71JjFyqxKsYI"
    private lateinit var binding: FragmentYoutubePreviewBinding
    lateinit var videoFragment: YouTubePlayerFragment

    private val model: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoutubePreviewBinding.inflate(inflater)
        return binding.root
    }

    // Destroy the video fragment when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        videoFragment.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.selectedVideo.observe(viewLifecycleOwner) {
            previewVideo(it.title, it.description, it.video)
        }

    }

    fun previewVideo(videoTitle: String, videoDescription: String, videoId: String) {
        binding.title = videoTitle
        binding.description = videoDescription

        videoFragment = YouTubePlayerFragment.newInstance()
        val fragmentManager = activity?.fragmentManager

        try {
            // Replace the existing fragment with the YouTubePlayerFragment
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.playerFragment, videoFragment) // replace video fragment in playerFragment
            fragmentTransaction?.commit() // apply the change
            videoFragment?.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    if (!p2) {
                        p1?.loadVideo(videoId)
                        p1?.play()
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {
                    Toast.makeText(
                        context,
                        "Failed to initialize YouTube player",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }


}