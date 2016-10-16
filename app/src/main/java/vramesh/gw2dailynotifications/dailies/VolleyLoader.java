package vramesh.gw2dailynotifications.dailies;

import android.content.Context;
import android.support.v4.content.Loader;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import vramesh.gw2dailynotifications.http.HttpRequestQueue;

/**
 * An asynchronous loader that can be used with Volley http requests.
 *
 * R is the response representation, U is the UI representation
 *
 */
public abstract class VolleyLoader<R, U> extends Loader<U> {

    private Request<R> mCurrentRequest;

    public VolleyLoader(Context context) {
        super(context);
    }

    /**
     * Describe how to convert a R (response representation) into a U (ui representation)
     * @param in the incoming data representation
     * @return "in" transformed into it's corresponding UI representation
     */
    abstract U toUIRepresentation(R in);

    /**
     * Make the volley request that should be made to load data into this loader
     * @return the Request
     */
    abstract Request<R> makeRequest();

    @Override
    protected void onForceLoad() {
        mCurrentRequest = makeRequest();
        HttpRequestQueue.getInstance(getContext()).getRequestQueue().add(mCurrentRequest);
    }

    private void cancelCurrentRequest() {
        HttpRequestQueue.getInstance(getContext()).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return request.equals(mCurrentRequest);
            }
        });
    }

    @Override
    protected boolean onCancelLoad() {
        cancelCurrentRequest();
        return false;
    }

    @Override
    protected void onStopLoading() {
        cancelCurrentRequest();
    }

    @Override
    protected void onAbandon() {
        cancelCurrentRequest();
    }

    @Override
    protected void onReset() {
        cancelCurrentRequest();
    }


}
