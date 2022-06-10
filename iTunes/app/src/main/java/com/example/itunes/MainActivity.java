package com.example.itunes;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.itunes.Model.MusicRoot;
import com.example.itunes.RestApi.ManagerAll;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.itunes.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_music, R.id.navigation_movie, R.id.navigation_book)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Call<MusicRoot> MusicData = ManagerAll.Web().ManagerMusic("tr","tarkan","music");
        MusicData.enqueue(new Callback<MusicRoot>() {
            @Override
            public void onResponse(Call<MusicRoot> call, Response<MusicRoot> response) {
                if (response.isSuccessful())
                {
                    try {
                        Log.i("rtrtrt", response.body().getResults().get(1).getPreviewUrl().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().
                                setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build());
                        mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(response.body().getResults().get(1).getPreviewUrl()));
                        mediaPlayer.prepare();
                        //mediaPlayer.start();
                    }catch (Exception Ex)
                    {

                    }

                }
            }

            @Override
            public void onFailure(Call<MusicRoot> call, Throwable t) {

            }
        });

    }

}