package chat.at.gse.com.chatapp.view

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import chat.at.gse.com.chatapp.R
import chat.at.gse.com.chatapp.model.Message
import com.google.android.exoplayer2.ui.PlayerView
import android.os.Handler
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class AudioMessageView(view:View): RecyclerView.ViewHolder(view) {
    val txtMessageBody:TextView
    val playerView: PlayerView

    init {
        txtMessageBody = view.findViewById(R.id.text_message_body)
        playerView = view.findViewById(R.id.audio_player)
    }

    fun bind(message:Message){
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


        playerView.player = player

    }

}