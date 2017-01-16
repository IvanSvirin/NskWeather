package com.example.ivansv.nskweather.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.view.fragment.StartFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new StartFragment())
                .commit();
    }
}
