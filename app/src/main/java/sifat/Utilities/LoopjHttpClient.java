package sifat.Utilities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static sifat.Utilities.CommonUtilities.LOG_TAG_WEB;


/**
 * Created by sifat on 11/3/2015.
 */
public class LoopjHttpClient {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i(LOG_TAG_WEB, url);
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams requestParams, AsyncHttpResponseHandler responseHandler) {
        client.post(url, requestParams, responseHandler);
    }

    public static void debugLoopJ(String TAG, String methodName, String url, RequestParams requestParams, byte[] response, Header[] headers, int statusCode, Throwable t, Context context) {


        if (headers != null) {
            for (Header h : headers) {
                String _h = String.format(Locale.US, "%s : %s", h.getName(), h.getValue());
                Log.i(LOG_TAG_WEB, _h);
            }

            if (t != null) {
                Log.i(LOG_TAG_WEB, "Throwable:" + t);
            }

            Log.e(TAG, "StatusCode: " + statusCode);

            if (response != null) {
                Log.i(LOG_TAG_WEB, "Response: " + new String(response));
                Toast.makeText(context, new String(response), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
