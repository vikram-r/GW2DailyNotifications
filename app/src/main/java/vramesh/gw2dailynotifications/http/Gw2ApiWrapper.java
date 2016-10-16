package vramesh.gw2dailynotifications.http;

import android.content.Context;

import com.android.volley.Response;

import vramesh.gw2dailynotifications.model.DailyResponse;


public class Gw2ApiWrapper {

    private static final String BASE_URL = "https://api.guildwars2.com/v2/achievements";

    public static JsonRequest<DailyResponse> makeDailyRequest(
            Response.Listener<DailyResponse> listener,
            Response.ErrorListener errorListener) {

        return new JsonRequest<>(
                BASE_URL + "/daily",
                DailyResponse.class,
                listener,
                errorListener
        );
    }

}
