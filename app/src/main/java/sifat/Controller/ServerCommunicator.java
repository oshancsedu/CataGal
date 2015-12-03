package sifat.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import sifat.Adapter.MemoAdapter;
import sifat.Database.DbOperator;
import sifat.Domain.MemoProductInfo;
import sifat.Utilities.LoopjHttpClient;
import sifat.catagal.MemoGenActivity;

import static sifat.Utilities.CommonUtilities.JSON_TAG_AREA_CODE;
import static sifat.Utilities.CommonUtilities.JSON_TAG_AREA_NAME;
import static sifat.Utilities.CommonUtilities.JSON_TAG_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.LOGIN_URL;
import static sifat.Utilities.CommonUtilities.LOG_TAG_WEB;
import static sifat.Utilities.CommonUtilities.MEMO_BASIC_INFO_URL;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PASSWORD;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_USERID;
import static sifat.Utilities.CommonUtilities.SHAREDPREF_TAG_USERID;
import static sifat.Utilities.CommonUtilities.changeActivity;
import static sifat.Utilities.CommonUtilities.getPref;
import static sifat.Utilities.CommonUtilities.showToast;

/**
 * Created by sifat on 11/22/2015.
 */
public class ServerCommunicator {

    public ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    HttpConnection http;
    DbOperator dbOperator;

    public ServerCommunicator(Context context) {
        this.context = context;
    }

    public void getMemoBasicInfo() {
        final String updateMemoInfo = MEMO_BASIC_INFO_URL;
        LoopjHttpClient.get(updateMemoInfo, null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                Log.i(LOG_TAG_WEB, "Success");
                setMemoBasicInfo(new String(responseBody));
                //LoopjHttpClient.debugLoopJ(LOG_TAG_WEB, "sendLocationDataToWebsite - success", updateMemoInfo, null, responseBody, headers, statusCode, null, context);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(LOG_TAG_WEB, "Fail");
                LoopjHttpClient.debugLoopJ(LOG_TAG_WEB, "sendLocationDataToWebsite - failure", updateMemoInfo, null, responseBody, headers, statusCode, error, context);
            }
        });
    }

    public void sendMemoInfo(String name) {
        addedProduct = MemoAdapter.addedProduct;
        showToast(context, "Size: " + addedProduct.size() + "\n" + addedProduct.get(0).getProductName());
    }

    public void login(final String userId, String password) {
        showToast(context, "User: " + userId + "\nPass:" + password);
        RequestParams requestParams = new RequestParams();
        requestParams.put(SERVER_REQUEST_USERID, userId);
        requestParams.put(SERVER_REQUEST_PASSWORD, password);
        final String loginWebSite = LOGIN_URL;

        LoopjHttpClient.get(loginWebSite, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                if (response.equalsIgnoreCase("true")) {
                    sharedPreferences = getPref(context);
                    editor = sharedPreferences.edit();
                    editor.putString(SHAREDPREF_TAG_USERID, userId);
                    editor.commit();
                    changeActivity(context, MemoGenActivity.class);
                } else
                    showToast(context, "Login Failed!");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast(context, "Please check your internet connection!");
            }
        });

    }


    private void setMemoBasicInfo(String info) {

        String areaName, areaCode, distributorName;
        Log.i(LOG_TAG_WEB, "Set info");
        dbOperator = DbOperator.getDbOperator(context);
        Log.i(LOG_TAG_WEB, info);
        try {
            JSONObject jsonObject = new JSONObject(info);
            Log.i(LOG_TAG_WEB, info);
            areaName = jsonObject.getString(JSON_TAG_AREA_NAME);
            Log.i(LOG_TAG_WEB, areaName);
            areaCode = jsonObject.getString(JSON_TAG_AREA_CODE);
            Log.i(LOG_TAG_WEB, areaCode);
            distributorName = jsonObject.getString(JSON_TAG_DISTRIBUTOR_NAME);
            Log.i(LOG_TAG_WEB, distributorName);

            dbOperator.open();
            dbOperator.updateMemoBasicInfo(distributorName, areaName, areaCode);
            dbOperator.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
