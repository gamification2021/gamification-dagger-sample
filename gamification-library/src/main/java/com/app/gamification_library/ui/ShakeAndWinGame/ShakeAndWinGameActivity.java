package com.app.gamification_library.ui.ShakeAndWinGame;

import android.animation.Animator;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.app.gamification_library.R;
import com.app.gamification_library.base.BaseActivity;
import com.app.gamification_library.databinding.ActivityShakeAndWinGameBinding;
import com.app.gamification_library.model.Reward;
import com.app.gamification_library.network.ApiResponse;
import com.app.gamification_library.utils.ViewModelFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;

public class ShakeAndWinGameActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private ShakeAndWinGameViewModel shakeAndWinGameViewModel;

    ActivityShakeAndWinGameBinding binding;
    LottieAnimationView eggCompletion;
    Reward reward;
    ShakeDetector shakeDetector;
    ImageView eggImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shake_and_win_game);
        eggCompletion = binding.eggCompletion;
        eggImage = binding.eggImage;

        AndroidInjection.inject(this);
        shakeAndWinGameViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShakeAndWinGameViewModel.class);
        shakeAndWinGameViewModel.paymentsResponse().observe(this, this::consumeResponse);
        shakeAndWinGameViewModel.hitLoginApi();

        ShakeOptions options = new ShakeOptions()
                .background(true)
                .interval(1000)
                .shakeCount(2)
                .sensibility(2.0f);

        shakeDetector = new ShakeDetector(options).start(getApplicationContext(), () -> {
            eggCompletion.setVisibility(View.VISIBLE);
            eggCompletion.setAnimation("shake_animation.json");
            eggCompletion.playAnimation();
            eggImage.setVisibility(View.GONE);
        });

        eggCompletion.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                successDialog();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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

    public void renderSuccessResponse(JsonElement jsonElement){
        Gson gson = new Gson();
        reward = gson.fromJson(jsonElement, Reward.class);
        binding.setReward(reward);
    }

    public void successDialog(){
        Dialog dialog = new Dialog(ShakeAndWinGameActivity.this, R.style.Base_Theme_AppCompat);
        dialog.setContentView(R.layout.success_dialog);
        Button okayBtn = dialog.findViewById(R.id.okayBtn);
        TextView rewardText = dialog.findViewById(R.id.rewardText);
        rewardText.setText(reward.getData().getDescription());
        okayBtn.setOnClickListener(view -> finish());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }
}