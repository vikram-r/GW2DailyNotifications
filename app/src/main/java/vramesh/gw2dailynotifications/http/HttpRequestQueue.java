package vramesh.gw2dailynotifications.http;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by vikram on 10/15/16.
 */

public class HttpRequestQueue {

    private static HttpRequestQueue mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private HttpRequestQueue(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized HttpRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HttpRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
}
