package com.app.gamification_library.ui.ShakeAndWinGame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.gamification_library.network.APIClient;
import com.app.gamification_library.network.ApiResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShakeAndWinGameViewModel extends ViewModel {

    private APIClient apiClient;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    @Inject
    public ShakeAndWinGameViewModel(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    public LiveData<ApiResponse> paymentsResponse() {
        return responseLiveData;
    }

    public void hitLoginApi() {
        disposables.add(apiClient.getReward()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> {
                    responseLiveData.setValue(ApiResponse.loading());
                })
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}
