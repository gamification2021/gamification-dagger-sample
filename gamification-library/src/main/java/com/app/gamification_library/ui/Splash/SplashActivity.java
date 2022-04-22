package com.app.gamification_library.ui.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.app.gamification_library.R;
import com.app.gamification_library.base.BaseActivity;
import com.app.gamification_library.ui.ShakeAndWin.ShakeAndWinActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, ShakeAndWinActivity.class));
            finish();
        }, 2000);
    }
}