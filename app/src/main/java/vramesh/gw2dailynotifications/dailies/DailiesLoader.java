package vramesh.gw2dailynotifications.dailies;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import vramesh.gw2dailynotifications.http.Gw2ApiWrapper;
import vramesh.gw2dailynotifications.model.Daily;
import vramesh.gw2dailynotifications.model.DailyResponse;

public class DailiesLoader extends VolleyLoader<DailyResponse, List<DailyListing>> {

    final String TAG = "GW2DailyNotifications";

    public DailiesLoader(Context context) {
        super(context);
    }

    @Override
    List<DailyListing> toUIRepresentation(DailyResponse in) {
        List<DailyListing> allDailies = new ArrayList<>();

        //gross
        for (Daily pveDaily : in.getPve()) {
            allDailies.add(toDailyListing(pveDaily, "PvE"));
        }
        for (Daily pvpDaily : in.getPvp()) {
            allDailies.add(toDailyListing(pvpDaily, "PvP"));
        }
        for (Daily wvwDaily : in.getWvw()) {
            allDailies.add(toDailyListing(wvwDaily, "WvW"));
        }
        for (Daily specialDaily : in.getSpecial()) {
            allDailies.add(toDailyListing(specialDaily, "Special"));
        }

        return allDailies;
    }

    private DailyListing toDailyListing(Daily d, String gameMode) {
        //todo make this awesome
        return new DailyListing(d.getId() + "", gameMode);
    }

    @Override
    Request<DailyResponse> makeRequest() {
        Response.Listener<DailyResponse> callback = new Response.Listener<DailyResponse>() {
            @Override
            public void onResponse(DailyResponse response) {
                //update the ui
                deliverResult(toUIRepresentation(response));
            }
        };
        Response.ErrorListener errorCallback = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error retreiving response: " + error.getMessage());
            }
        };

        return Gw2ApiWrapper.makeDailyRequest(callback, errorCallback);
    }
}
