package vramesh.gw2dailynotifications.dailies;

import android.content.Context;
import android.support.v4.content.Loader;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import vramesh.gw2dailynotifications.http.HttpRequestQueue;

/**
 * An asynchronous loader that can be used with Volley http requests. An implementor should call
 * {@link #executeRequest(Request)} whenever a volley request is made. VolleyLoader will manage the
 * request in accordance with it's own lifecycle.
 *
 */
public abstract class VolleyLoader<U> extends Loader<U> {

    private List<Request> mCurrentRequests = new ArrayList<>();

    public VolleyLoader(Context context) {
        super(context);
    }

    /**
     * Make any volley requests necessary to load data into this loader
     */
    abstract void loadData();

    @Override
    protected void onForceLoad() {
        loadData();
    }

    /**
     * Keep track of and execute a volley request. VolleyLoader will manage the request in accordance
     * with its own lifecycle.
     * @param r the request to execute
     */
    void executeRequest(Request r) {
        mCurrentRequests.add(r);
        HttpRequestQueue.getInstance(getContext()).getRequestQueue().add(r);
    }

    private void cancelCurrentRequests() {
        HttpRequestQueue.getInstance(getContext()).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return mCurrentRequests.contains(request);
            }
        });
    }

    @Override
    protected boolean onCancelLoad() {
        cancelCurrentRequests();
        return false;
    }

    @Override
    protected void onStopLoading() {
        cancelCurrentRequests();
    }

    @Override
    protected void onAbandon() {
        cancelCurrentRequests();
    }

    @Override
    protected void onReset() {
        cancelCurrentRequests();
    }


}
