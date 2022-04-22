package com.app.gamification_library.di.module;


import com.app.gamification_library.ui.ShakeAndWin.ShakeAndWinActivity;
import com.app.gamification_library.ui.ShakeAndWinGame.ShakeAndWinGameActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract ShakeAndWinActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract ShakeAndWinGameActivity bindShakeAndWinGameActivity();
}
