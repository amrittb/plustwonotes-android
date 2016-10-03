package com.amrittwanabasu.plustwonotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

public class SplashActivity extends AppCompatActivity {

    /**
     * Hero Image KenBurns Effect Transaction duration.
     */
    public static final int HERO_IMAGE_TRANSACTION_DURATION = 40000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        KenBurnsView heroImage = (KenBurnsView) findViewById(R.id.hero_bg);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(HERO_IMAGE_TRANSACTION_DURATION, new AccelerateDecelerateInterpolator());
        heroImage.setTransitionGenerator(generator);
        heroImage.restart();
    }
}
