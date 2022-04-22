package com.app.gamification_library.ui.ShakeAndWin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.app.gamification_library.R;
import com.app.gamification_library.base.BaseActivity;
import com.app.gamification_library.databinding.ActivityShakeAndWinBinding;
import com.app.gamification_library.model.Main;
import com.app.gamification_library.network.ApiResponse;
import com.app.gamification_library.ui.ShakeAndWinGame.ShakeAndWinGameActivity;
import com.app.gamification_library.utils.ViewModelFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ShakeAndWinActivity extends BaseActivity implements OnClickListener {

    @Inject
    ViewModelFactory viewModelFactory;
    private MainViewModel mainViewModel;
    ActivityShakeAndWinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ShakeAndWinActivity.this, R.layout.activity_shake_and_win);
        AndroidInjection.inject(this);

        binding.setListener(this);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        mainViewModel.paymentsResponse().observe(this, this::consumeResponse);
        mainViewModel.hitLoginApi();
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                break;

            case SUCCESS:
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                break;
            default:
                break;
        }
    }

    public void renderSuccessResponse(JsonElement jsonElement) {
        Gson gson = new Gson();
        Main main = gson.fromJson(jsonElement, Main.class);
        binding.setMain(main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.playNow) {
            Intent i = new Intent(getApplicationContext(), ShakeAndWinGameActivity.class);
            startActivity(i);
        }
    }
}