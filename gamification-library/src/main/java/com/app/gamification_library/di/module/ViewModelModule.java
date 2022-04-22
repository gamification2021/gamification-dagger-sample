package com.app.gamification_library.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.gamification_library.di.util.ViewModelKey;
import com.app.gamification_library.ui.ShakeAndWin.MainViewModel;
import com.app.gamification_library.ui.ShakeAndWinGame.ShakeAndWinGameViewModel;
import com.app.gamification_library.utils.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindListViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShakeAndWinGameViewModel.class)
    abstract ViewModel bindShakeAndWinModel(ShakeAndWinGameViewModel shakeAndWinGameViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
