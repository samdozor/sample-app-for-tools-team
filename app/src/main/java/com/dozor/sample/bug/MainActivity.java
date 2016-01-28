package com.dozor.sample.bug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mparticle.MParticle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //note that this is just a sample and will not work
        MParticle.start(this, "api key", "api secret");
    }
}
