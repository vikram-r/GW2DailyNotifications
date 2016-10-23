package vramesh.gw2dailynotifications.http;

import com.android.volley.Response;

import vramesh.gw2dailynotifications.model.DailiesResponse;
import vramesh.gw2dailynotifications.model.DailyInfo;


public class Gw2ApiWrapper {

    private static final String BASE_URL = "https://api.guildwars2.com/v2/achievements";

    public static JsonRequest<DailiesResponse> makeAllDailiesRequest(
            Response.Listener<DailiesResponse> listener,
            Response.ErrorListener errorListener) {

        return new JsonRequest<>(
                BASE_URL + "/daily",
                DailiesResponse.class,
                listener,
                errorListener
        );
    }

    public static JsonRequest<DailyInfo> makeDailyInfoRequest(
            int dailyId,
            Response.Listener<DailyInfo> listener,
            Response.ErrorListener errorListener) {
        return new JsonRequest<>(
                BASE_URL + "/" + dailyId,
                DailyInfo.class,
                listener,
                errorListener
        );    }

}
