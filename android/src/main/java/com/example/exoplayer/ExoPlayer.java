package com.example.exoplayer;

import android.content.Context;
import android.net.Uri;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.PluginMethod;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

@CapacitorPlugin(name = "ExoPlayer")
public class ExoPlayerPlugin extends Plugin {
    private SimpleExoPlayer player;
    private PlayerView playerView;

    @PluginMethod
    public void initialize(PluginCall call) {
        Context context = getContext();

        // Initialize ExoPlayer
        player = ExoPlayerFactory.newSimpleInstance(context);
        playerView = new PlayerView(context);

        // Configure PlayerView
        playerView.setPlayer(player);
        playerView.setLayoutParams(new FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        ));

        bridge.getActivity().runOnUiThread(() -> {
            FrameLayout layout = (FrameLayout) bridge.getWebView().getParent();
            layout.addView(playerView);
        });

        call.resolve();
    }

    @PluginMethod
    public void load(PluginCall call) {
        String url = call.getString("url");

        if (url == null) {
            call.reject("URL is required");
            return;
        }

        Context context = getContext();
        MediaSource mediaSource = new HlsMediaSource.Factory(
            new DefaultDataSourceFactory(context, "exoplayer-codelab")
        ).createMediaSource(Uri.parse(url));

        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

        call.resolve();
    }

    @PluginMethod
    public void dispose(PluginCall call) {
        if (player != null) {
            player.release();
            player = null;
            call.resolve();
        } else {
            call.reject("Player is not initialized");
        }
    }
}
