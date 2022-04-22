package com.app.gamification_library.network;

import com.google.gson.JsonElement;

import javax.inject.Inject;

import io.reactivex.Observable;

public class APIClient {

    private final APIInterface apiInterface;

    @Inject
    public APIClient(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<JsonElement> getRepositories() {
        return apiInterface.getRepositories();
    }

    public Observable<JsonElement> getReward() {
        return apiInterface.getReward();
    }

}
