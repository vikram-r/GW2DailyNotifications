package vramesh.gw2dailynotifications.http;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * A volley Response Listener that executes a callback function after a set number of requests have
 * completed.
 */
public class BundledResponseListener<T> implements Response.Listener<T>, Response.ErrorListener {

    final String TAG = "GW2DailyNotifications";
    private static int mTotalRequests;
    private static int mRequestsComplete = 0;
    private BundledResponseCompleteListener<T> mListener;
    private List<T> mCurrentResults = new ArrayList<>();

    public BundledResponseListener(int totalRequests, BundledResponseCompleteListener<T> listener) {
        if (totalRequests <= 0) {
            throw new IllegalArgumentException("totalRequests must be at least 1");
        }
        mTotalRequests = totalRequests;
        mListener = listener;
    }

    @Override
    public void onResponse(T response) {
        mCurrentResults.add(response);
        if (++mRequestsComplete >= mTotalRequests) {
            //all requests have completed, execute callback
            mListener.onAllRequestsComplete(mCurrentResults);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        mRequestsComplete++;
        Log.e(TAG, error.getMessage());
    }

    /**
     * Callback interface to deliver result when all bundled requests are complete
     * @param <T>
     */
    public interface BundledResponseCompleteListener<T> {
        /**
         * Called when ALL responses have been received
         */
        void onAllRequestsComplete(List<T> response);
    }
}
