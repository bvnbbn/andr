package com.example.vikas.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.vikas.movie.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by vikas on 29/4/17.
 */

public class VedeoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    private static final String Developer_key="AIzaSyAzCeqb2Wyr1OPW1wsmBrkbP6VFs8pgyO4";
    private static  String you_tube_id = null;
    private YouTubePlayerView youTubePlayerView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private MyPlaybackEventListener playbackEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.you_tube_vedeo);
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(Developer_key,this);
        Bundle extras= getIntent().getExtras();
        if(extras!=null)
        {
            you_tube_id=  extras.getString("you_tube_key");
        }
        playbackEventListener=new MyPlaybackEventListener();

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player,boolean wasRestored)
    {
        player.setPlaybackEventListener(playbackEventListener);
        if(!wasRestored)
        {
           // player.cueVideo(you_tube_id);if we do not want to play vedeo automatically
            player.loadVideo(you_tube_id);// if we want to play vedeo automatically

           // player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);//hiede all features of you tube
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason)
    {
        if(errorReason.isUserRecoverableError())
        {
            errorReason.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();
        }
        else
        {
            String errorMessage=String.format(getString(R.string.error_player),errorReason.toString());
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == RECOVERY_DIALOG_REQUEST)
        {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Developer_key, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider()
    {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
    private void showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    //adding two subclasses for activity click events
    // like pause the vedeo and forwarding the vedeo and stop the vedeo.

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener,YouTubePlayer.OnFullscreenListener
    {

        @Override
        public void onPlaying()
        {

        }

        @Override
        public void onPaused()
        {
            //called when vedeo is paused
            showMessage("Paused");
        }

        @Override
        public void onStopped()
        {
           // showMessage("Stopped");
        }

        @Override
        public void onBuffering(boolean b)
        {
            showMessage("Loading Vedeo");
        }

        @Override
        public void onSeekTo(int i)
        {

        }

        @Override
        public void onFullscreen(boolean b)
        {

        }
    }

}
