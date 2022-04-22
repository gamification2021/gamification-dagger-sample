package com.app.gamification_library.network;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    @GET(APIConstants.URLs.MAIN_URL)
    Observable<JsonElement> getRepositories();

    @GET(APIConstants.URLs.REWARD_URL)
    Observable<JsonElement> getReward();

}
