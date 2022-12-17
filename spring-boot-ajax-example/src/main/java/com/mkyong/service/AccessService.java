package com.mkyong.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccessService {
    private OkHttpClient client;
    private Response response;
    private String siteName;
    String key = "1b3ca428-61cd-49c7-9a84-49d880df1280";

    public JSONObject getAccess() {
        client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.builtwith.com/v20/api.json?KEY=" + key + "&LOOKUP="+getSiteName()).build();
        try {
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public JSONArray returnResults() throws JSONException {
        JSONArray Results = getAccess().getJSONArray("Results");
        return Results;
    }

    public JSONObject returnResult() throws JSONException {
        JSONArray results = returnResults();
        JSONObject result = null;
        for (int i = 0; i < results.length(); i++) {
            result = results.getJSONObject(i);
        }
        return result;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }


}




