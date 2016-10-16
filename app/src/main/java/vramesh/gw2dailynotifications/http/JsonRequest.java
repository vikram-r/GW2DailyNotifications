package vramesh.gw2dailynotifications.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Created by vikram on 10/15/16.
 */

public class JsonRequest<T> extends Request<T> {

    private final Gson mGson = new Gson();
    private final Class<T> mClazz;
    private final Response.Listener<T> mListener;

    public JsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mClazz = clazz;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    mGson.fromJson(json, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response)
            );
        }  catch (JsonSyntaxException | UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
