package vramesh.gw2dailynotifications.dailies;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import vramesh.gw2dailynotifications.http.BundledResponseListener;
import vramesh.gw2dailynotifications.http.Gw2ApiWrapper;
import vramesh.gw2dailynotifications.model.Daily;
import vramesh.gw2dailynotifications.model.DailiesResponse;
import vramesh.gw2dailynotifications.model.DailyInfo;

public class DailiesLoader extends VolleyLoader<List<DailyListing>> {

    final String TAG = "GW2DailyNotifications";

    public DailiesLoader(Context context) {
        super(context);
    }

    List<DailyListing> toUIRepresentation(List<DailyInfo> in) {
        List<DailyListing> allDailies = new ArrayList<>();
        for (DailyInfo d: in) {
            allDailies.add(toDailyListing(d));
        }
        return allDailies;
    }

    private DailyListing toDailyListing(DailyInfo d) {
        //todo make this awesome
        return new DailyListing(d.getName() + "", d.getDescription());
    }

    @Override
    void loadData() {
        Response.Listener<DailiesResponse> callback = new Response.Listener<DailiesResponse>() {
            @Override
            public void onResponse(final DailiesResponse response) {
                // Only update the ui when ALL dailies have been loaded
                BundledResponseListener<DailyInfo> bundledListener = new BundledResponseListener<>(
                        response.getAllDailies().size(),
                        new BundledResponseListener.BundledResponseCompleteListener<DailyInfo>() {
                            @Override
                            public void onAllRequestsComplete(List<DailyInfo> response) {
                                deliverResult(toUIRepresentation(response));
                            }
                        }
                );

                // make a request to get more info for each daily
                for (Daily daily : response.getAllDailies()) {
                    executeRequest(Gw2ApiWrapper.makeDailyInfoRequest(daily.getId(), bundledListener, bundledListener));
                }
            }
        };
        Response.ErrorListener errorCallback = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error retreiving response: " + error.getMessage());
            }
        };

        executeRequest(Gw2ApiWrapper.makeAllDailiesRequest(callback, errorCallback));
    }
}
