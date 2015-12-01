package sifat.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sifat.Adapter.MemoAdapter;
import sifat.Database.DbOperator;
import sifat.Domain.MemoProductInfo;
import sifat.Utilities.LoopjHttpClient;
import sifat.catagal.MemoGenActivity;

import static sifat.Utilities.CommonUtilities.JSON_TAG_AREA_CODE;
import static sifat.Utilities.CommonUtilities.JSON_TAG_AREA_NAME;
import static sifat.Utilities.CommonUtilities.JSON_TAG_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.LOG_TAG_WEB;
import static sifat.Utilities.CommonUtilities.MEMO_BASIC_INFO_URL;
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
        final String loginWebsite = MEMO_BASIC_INFO_URL;
        Toast.makeText(context, loginWebsite, Toast.LENGTH_SHORT).show();

        LoopjHttpClient.get(loginWebsite, null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                Log.i(LOG_TAG_WEB, "Success");
                setMemoBasicInfo(new String(responseBody));
                LoopjHttpClient.debugLoopJ(LOG_TAG_WEB, "sendLocationDataToWebsite - success", loginWebsite, null, responseBody, headers, statusCode, null, context);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(LOG_TAG_WEB, "Fail");
                LoopjHttpClient.debugLoopJ(LOG_TAG_WEB, "sendLocationDataToWebsite - failure", loginWebsite, null, responseBody, headers, statusCode, error, context);
            }
        });
    }

    public void sendMemoInfo(String name) {
        addedProduct = MemoAdapter.addedProduct;
        showToast(context, "Size: " + addedProduct.size() + "\n" + addedProduct.get(0).getProductName());
    }

    public void login(String userId, String password) {
        showToast(context, "User: " + userId + "\nPass:" + password);
        changeActivity(context, MemoGenActivity.class);
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

            showToast(context, areaName);
            showToast(context, areaCode);
            showToast(context, distributorName);
            dbOperator.open();
            dbOperator.updateMemoBasicInfo(distributorName, areaName, areaCode);
            dbOperator.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void changeActivity(Context context, Class toClass) {
        Intent intent = new Intent(context, toClass);
        context.startActivity(intent);
    }
}
