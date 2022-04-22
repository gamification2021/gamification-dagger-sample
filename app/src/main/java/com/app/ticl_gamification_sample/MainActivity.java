package com.app.ticl_gamification_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.gamification_library.ui.ShakeAndWin.ShakeAndWinActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_and_win);

        startActivity(new Intent(getApplicationContext(), ShakeAndWinActivity.class));
    }
}