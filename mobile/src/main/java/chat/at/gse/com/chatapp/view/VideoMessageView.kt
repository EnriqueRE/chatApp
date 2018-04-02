package chat.at.gse.com.chatapp.view

import android.app.Dialog
import android.net.Uri
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Message
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import android.widget.FrameLayout
import android.widget.ImageView
import com.google.android.exoplayer2.source.MediaSource
import chat.at.gse.com.chatapp.MainActivity
import android.support.v4.content.ContextCompat
import android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import android.util.Log
import android.view.ViewGroup
import com.google.android.exoplayer2.ui.PlaybackControlView


class VideoMessageView(view:View): RecyclerView.ViewHolder(view) {
    val txtMessageBody: TextView
    val playerView: PlayerView
    private val mExoPlayerView: SimpleExoPlayerView? = null
    private val mVideoSource: MediaSource? = null
    private var mExoPlayerFullscreen = false
    private var mFullScreenButton: FrameLayout? = null
    private var mFullScreenIcon: ImageView? = null
    private var mFullScreenDialog: Dialog? = null
    val view:View

    init {
        txtMessageBody = view.findViewById(R.id.text_message_body)
        playerView = view.findViewById(R.id.video_player)
        this.view = view
    }

    fun bind(message: Message){
        val payload = message.messageBody.messagePayload;

        if (payload.text == "") {
            txtMessageBody.visibility = View.GONE
        } else {
            txtMessageBody.text = payload.text
        }

        // 1. Create a default TrackSelector
        val mainHandler = Handler()
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        val loadControl = DefaultLoadControl()
        val dataSourceFactory = DefaultDataSourceFactory(playerView.context, "ExoPlayerChatbots")
        val extractorsFactory = DefaultExtractorsFactory()
        val mediaSource = ExtractorMediaSource(Uri.parse(payload.attachment?.url),
                dataSourceFactory,
                extractorsFactory,
                mainHandler,
                null)


        // 2. Create the player
        val player = ExoPlayerFactory.newSimpleInstance(playerView.context, trackSelector, loadControl)
        player.prepare(mediaSource)
        player.playWhenReady = true

        initFullscreenButton()
        initFullscreenDialog()

        playerView.player = player

    }

    private fun initFullscreenDialog() {

        mFullScreenDialog = object : Dialog(view.context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            override fun onBackPressed() {
                if (mExoPlayerFullscreen)
                    closeFullscreenDialog()
                super.onBackPressed()
            }
        }
    }

    private fun initFullscreenButton(){
        var controlView = mExoPlayerView?.findViewById<PlaybackControlView>(R.id.exo_controller)
        mFullScreenIcon = controlView?.findViewById(R.id.exo_fullscreen_icon)
        mFullScreenButton = controlView?.findViewById(R.id.exo_fullscreen_button)

        mFullScreenButton?.setOnClickListener(View.OnClickListener {
            Log.d("VIDEO","click click click")
            if (!mExoPlayerFullscreen)
                openFullscreenDialog()
            else
                closeFullscreenDialog()
        })

    }

    private fun openFullscreenDialog() {

        (playerView.getParent() as ViewGroup).removeView(mExoPlayerView)
        mFullScreenDialog?.addContentView(mExoPlayerView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        mFullScreenIcon?.setImageDrawable(ContextCompat.getDrawable(playerView.context, R.drawable.ic_fullscreen_shrink))
        mExoPlayerFullscreen = true
        mFullScreenDialog?.show()
    }




    private fun closeFullscreenDialog() {

        (mExoPlayerView?.getParent() as ViewGroup).removeView(mExoPlayerView)
        view.findViewById<FrameLayout>(R.id.main_media_frame).addView(mExoPlayerView)
        mExoPlayerFullscreen = false
        mFullScreenDialog?.dismiss()
        mFullScreenIcon?.setImageDrawable(ContextCompat.getDrawable(playerView.context, R.drawable.ic_fullscreen_expand))
    }


}