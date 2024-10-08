package vn.edu.usth.weather;

import static vn.edu.usth.weather.R.id.*;
import static vn.edu.usth.weather.R.id.action_edit;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import android.os.AsyncTask;


import androidx.appcompat.app.AppCompatActivity;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;


import vn.edu.usth.weather.ViewAdapter;


public class WeatherActivity extends AppCompatActivity {

//    private Handler handler;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        set tool bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // set view pager
        FragmentManager fm = getSupportFragmentManager();
        ViewAdapter pagerAdapter = new ViewAdapter(fm, getLifecycle());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setCurrentItem(0);

        // tab layout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Hanoi, Viet Nam"));
        tabLayout.addTab(tabLayout.newTab().setText("Paris, France"));

        // connecting tab layout to adapter
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        // Simulate a network request using a thread
//        handler = new Handler(Looper.getMainLooper());
//        simulateNetworkRequest();

        new NetworkRequestTask().execute();


    }

//    private void simulateNetworkRequest() {
//        // Create a new thread to simulate the network request
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    // Simulate network delay (e.g., 3 seconds)
//                    Thread.sleep(9000);
//
//                    // After the "network request" is completed, update the UI using the Handler
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Show a toast message on the main thread
//                            Toast.makeText(WeatherActivity.this, "Network request completed!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//
//    }

    private class NetworkRequestTask extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Thread.sleep(4000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return getString(R.string.networkcomplete);
        }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(WeatherActivity.this, s,Toast.LENGTH_SHORT).show();
    }
}


@Override
    protected void onStart() {
        super.onStart();
        MediaPlayer mPlayer = MediaPlayer.create(WeatherActivity.this, R.raw.music);
        mPlayer.start();
        Log.i("start", "On start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pause", "On pause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop", "On stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "On destroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.bottom, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reload:
                Toast.makeText(this, "Reload", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_notification:
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_edit:
                Toast.makeText(this, "Editting", Toast.LENGTH_SHORT).show();
                break;
        }
//        if (item.getItemId() == R.id.action_reload) {
//            Toast.makeText(this, "Reload", Toast.LENGTH_SHORT).show();
//        } else if (item.getItemId() == R.id.action_notification) {
//            Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
//        } else if (item.getItemId() == R.id.action_edit) {
//            Toast.makeText(this, "Editting", Toast.LENGTH_SHORT).show();
//        }
//
//        return false;
        return super.onOptionsItemSelected(item);
    }
}