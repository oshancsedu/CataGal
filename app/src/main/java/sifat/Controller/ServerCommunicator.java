package sifat.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import sifat.Adapter.MemoAdapter;
import sifat.Database.DbOperator;
import sifat.Domain.IntegratedProductInfo;
import sifat.Domain.MemoBasicInfo;
import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductCommonInfo;
import sifat.Utilities.LoopjHttpClient;
import sifat.catagal.MemoGenActivity;

import static sifat.Utilities.CommonUtilities.JSON_TAG_AREA_CODE;
import static sifat.Utilities.CommonUtilities.JSON_TAG_AREA_NAME;
import static sifat.Utilities.CommonUtilities.JSON_TAG_BANNER;
import static sifat.Utilities.CommonUtilities.JSON_TAG_COST_PER_UNIT;
import static sifat.Utilities.CommonUtilities.JSON_TAG_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.JSON_TAG_HEADER;
import static sifat.Utilities.CommonUtilities.JSON_TAG_IMAGES;
import static sifat.Utilities.CommonUtilities.JSON_TAG_INGREDIENT;
import static sifat.Utilities.CommonUtilities.JSON_TAG_MEMO_BASIC_INFO_ARRAY;
import static sifat.Utilities.CommonUtilities.JSON_TAG_MRP1;
import static sifat.Utilities.CommonUtilities.JSON_TAG_MRP1_TITLE;
import static sifat.Utilities.CommonUtilities.JSON_TAG_MRP2;
import static sifat.Utilities.CommonUtilities.JSON_TAG_MRP2_TITLE;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PACKING;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_COMMON_INFO_ARRAY;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_CONTAINER;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_DETAIL_INFO_ARRAY;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_ID;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_NAME;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_QUANTITY;
import static sifat.Utilities.CommonUtilities.JSON_TAG_PRODUCT_SIZE;
import static sifat.Utilities.CommonUtilities.JSON_TAG_SELLING_UNIT;
import static sifat.Utilities.CommonUtilities.JSON_TAG_VALIDITY;
import static sifat.Utilities.CommonUtilities.LOGIN_URL;
import static sifat.Utilities.CommonUtilities.LOG_TAG_WEB;
import static sifat.Utilities.CommonUtilities.MEMO_BASIC_INFO_URL;
import static sifat.Utilities.CommonUtilities.MEMO_RECEIVE_URL;
import static sifat.Utilities.CommonUtilities.PRODUCT_INFO_RECEIVE_URL;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_AREA_CODE;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_AREA_NAME;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_COMMENT;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PASSWORD;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PRODUCT_CARTON;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PRODUCT_COST;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PRODUCT_NAME;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PRODUCT_PACKET;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PRODUCT_SIZE;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_PRODUCT_UNIT;
import static sifat.Utilities.CommonUtilities.SERVER_REQUEST_SUPPLY_DATE;
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
    public ArrayList<ProductCommonInfo> productCommonInfos = new ArrayList<>();
    public ArrayList<MemoBasicInfo> memoBasicInfos = new ArrayList<>();
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    HttpConnection http;
    DbOperator dbOperator;
    private ArrayList<IntegratedProductInfo> ingrProInfos = new ArrayList<>();

    public ServerCommunicator(Context context) {
        this.context = context;
    }

    public void getMemoBasicInfo() {
        final String updateMemoInfo = MEMO_BASIC_INFO_URL;
        sharedPreferences = getPref(context);
        String userID = sharedPreferences.getString(SHAREDPREF_TAG_USERID, "");
        RequestParams requestParams = new RequestParams();
        requestParams.put(SERVER_REQUEST_USERID, userID);
        LoopjHttpClient.get(updateMemoInfo, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                Log.i(LOG_TAG_WEB, "Success");
                setMemoBasicInfo(new String(responseBody));
                //LoopjHttpClient.debugLoopJ(LOG_TAG_WEB, "sendLocationDataToWebsite - success", updateMemoInfo, null, responseBody, headers, statusCode, null, context);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(LOG_TAG_WEB, "Fail");
                showToast(context, "Please check your internet connection!");
                //LoopjHttpClient.debugLoopJ(LOG_TAG_WEB, "sendLocationDataToWebsite - failure", updateMemoInfo, null, responseBody, headers, statusCode, error, context);
            }
        });
    }


    public void getProductInfo() {
        final String productInfoURL = PRODUCT_INFO_RECEIVE_URL;
        LoopjHttpClient.get(productInfoURL, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i(LOG_TAG_WEB, new String(responseBody));
                setProductInfo(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast(context, "Please check your internet connection!");
            }
        });
    }

    public void sendMemoInfo(String areaName, String areaCode, String distributorName, String supplyDate) {
        addedProduct = MemoAdapter.addedProduct;
        sharedPreferences = getPref(context);
        String productName = "", productSize = "", productCost = "", carton = "", packet = "", comment = "", userID, productUnit = "";
        userID = sharedPreferences.getString(SHAREDPREF_TAG_USERID, "");
        showToast(context, "Size: " + addedProduct.size() + "\n" + addedProduct.get(0).getProductName());

        int size = addedProduct.size();
        for (int i = 0; i < size; i++) {
            productName = productName + addedProduct.get(i).getProductName() + " $";
            productSize = productSize + addedProduct.get(i).getProductSize() + " $";
            productCost = productCost + addedProduct.get(i).getCost() + " $";
            carton = carton + addedProduct.get(i).getCarton() + " $";
            packet = packet + addedProduct.get(i).getPacket() + " $";
            comment = comment + addedProduct.get(i).getComment() + " $";
            productUnit = productUnit + addedProduct.get(i).getSellingUnit() + " $";
        }

        RequestParams requestParams = new RequestParams();
        requestParams.put(SERVER_REQUEST_AREA_CODE, areaCode);
        requestParams.put(SERVER_REQUEST_AREA_NAME, areaName);
        requestParams.put(SERVER_REQUEST_DISTRIBUTOR_NAME, distributorName);
        requestParams.put(SERVER_REQUEST_SUPPLY_DATE, supplyDate);
        requestParams.put(SERVER_REQUEST_USERID, userID);
        requestParams.put(SERVER_REQUEST_PRODUCT_NAME, productName);
        requestParams.put(SERVER_REQUEST_PRODUCT_SIZE, productSize);
        requestParams.put(SERVER_REQUEST_PRODUCT_COST, productCost);
        requestParams.put(SERVER_REQUEST_PRODUCT_UNIT, productUnit);
        requestParams.put(SERVER_REQUEST_PRODUCT_CARTON, carton);
        requestParams.put(SERVER_REQUEST_PRODUCT_PACKET, packet);
        requestParams.put(SERVER_REQUEST_COMMENT, comment);
        /*String url=SERVER_REQUEST_AREA_CODE+"="+areaCode+"&"+
                SERVER_REQUEST_AREA_NAME+"="+areaName+"&"+
                SERVER_REQUEST_DISTRIBUTOR_NAME+"="+distributorName+"&"+
                SERVER_REQUEST_SUPPLY_DATE+"="+supplyDate+"&"+
                SERVER_REQUEST_USERID+"="+userID+"&"+
                SERVER_REQUEST_PRODUCT_NAME+"="+productName+"&"+
                SERVER_REQUEST_PRODUCT_SIZE+"="+productSize+"&"+
                SERVER_REQUEST_PRODUCT_COST+"="+productCost+"&"+
                SERVER_REQUEST_PRODUCT_UNIT+"="+productUnit+"&"+
                SERVER_REQUEST_PRODUCT_CARTON+"="+carton+"&"+
                SERVER_REQUEST_PRODUCT_PACKET+"="+packet+"&"+
                SERVER_REQUEST_COMMENT+"="+comment;*/

        final String memoReceiveUrl = MEMO_RECEIVE_URL;
        //Log.i(LOG_TAG_WEB,memoReceiveUrl+"?"+url);
        LoopjHttpClient.get(memoReceiveUrl, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast(context, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast(context, "Please check your internet connection!");
            }
        });
    }

    public void login(final String userId, String password) {
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
            JSONArray memoBasicInfosJson = jsonObject.getJSONArray(JSON_TAG_MEMO_BASIC_INFO_ARRAY);
            int size = memoBasicInfosJson.length();
            MemoBasicInfo memoBasicInfo;
            for (int i = 0; i < size; i++) {
                JSONObject childJson = memoBasicInfosJson.optJSONObject(i);

                areaName = childJson.getString(JSON_TAG_AREA_NAME);
                Log.i(LOG_TAG_WEB, areaName);
                areaCode = childJson.getString(JSON_TAG_AREA_CODE);
                Log.i(LOG_TAG_WEB, areaCode);
                distributorName = childJson.getString(JSON_TAG_DISTRIBUTOR_NAME);
                Log.i(LOG_TAG_WEB, distributorName);
                memoBasicInfo = new MemoBasicInfo(areaName, distributorName, areaCode);
                memoBasicInfos.add(memoBasicInfo);
            }
            dbOperator.open();
            dbOperator.updateMemoBasicInfo(memoBasicInfos);
            dbOperator.close();

        } catch (JSONException e) {
            showToast(context, "Unable to Update!");
        }
    }

    private void setProductInfo(String info) {
        Log.i(LOG_TAG_WEB, info);
        ProductCommonInfo productCommonInfo;
        IntegratedProductInfo ingrProInfo;
        try {
            JSONObject jsonObject = new JSONObject(info);
            JSONArray productsCommonInfo = jsonObject.getJSONArray(JSON_TAG_PRODUCT_COMMON_INFO_ARRAY);
            int size = productsCommonInfo.length();
            for (int i = 0; i < size; i++) {
                JSONObject childJson = productsCommonInfo.optJSONObject(i);
                productCommonInfo = new ProductCommonInfo(childJson.getInt(JSON_TAG_PRODUCT_ID),
                        childJson.getString(JSON_TAG_HEADER),
                        childJson.getString(JSON_TAG_BANNER),
                        childJson.getString(JSON_TAG_INGREDIENT));
                productCommonInfos.add(productCommonInfo);

            }

            JSONArray ingrProJsonInfos = jsonObject.getJSONArray(JSON_TAG_PRODUCT_DETAIL_INFO_ARRAY);
            size = ingrProJsonInfos.length();
            for (int i = 0; i < size; i++) {
                JSONObject childJson = ingrProJsonInfos.optJSONObject(i);
                ingrProInfo = new IntegratedProductInfo(
                        childJson.getInt(JSON_TAG_PRODUCT_ID),
                        childJson.getString(JSON_TAG_PRODUCT_NAME),
                        childJson.getString(JSON_TAG_PRODUCT_SIZE),
                        childJson.getString(JSON_TAG_PRODUCT_CONTAINER),
                        childJson.getString(JSON_TAG_PRODUCT_QUANTITY),
                        childJson.getString(JSON_TAG_VALIDITY),
                        childJson.getString(JSON_TAG_MRP1_TITLE),
                        childJson.getInt(JSON_TAG_MRP1),
                        childJson.getString(JSON_TAG_MRP2_TITLE),
                        childJson.getInt(JSON_TAG_MRP2),
                        childJson.getInt(JSON_TAG_HEADER),
                        childJson.getString(JSON_TAG_PACKING),
                        childJson.getString(JSON_TAG_SELLING_UNIT),
                        childJson.getInt(JSON_TAG_COST_PER_UNIT),
                        childJson.getString(JSON_TAG_IMAGES)
                );
                ingrProInfos.add(ingrProInfo);
            }

            dbOperator = DbOperator.getDbOperator(context);
            dbOperator.open();
            dbOperator.updateProductCommonInfo(productCommonInfos, true);
            dbOperator.updateProductDetailInfo(ingrProInfos, true);
            dbOperator.close();

        } catch (JSONException e) {

            showToast(context, "Unable to update!");
        }
    }
}