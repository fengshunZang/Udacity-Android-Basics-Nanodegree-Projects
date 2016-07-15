package com.zangfengshun.newsapp;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zang on 2016-07-14.
 */
public class JSONParser {
    private InputStream is = null;
    private String jsonString = "";
    private JSONObject jsonObject = null;

    //constructor
    public JSONParser() {
    }

    public JSONObject getJSONObjectFromUrl(String myUrl) {
        try {
            URL url = new URL(myUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setReadTimeout(1000);
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
        } catch (MalformedURLException e) {
            Log.v("JSONParser", "MalformedURLException");
        } catch (IOException e) {
            Log.v("JSONParser", "IOException during http-url connection");
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                line = line + "\n";
                sb.append(line);
            }
            is.close();
            jsonString = sb.toString().trim();
        } catch (IOException e) {
            Log.v("JSONParser", "IOException when inputting data");
        }

        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.v("JSONParser", "JSONExcpetion from string to JSON Object");
        }
        return jsonObject;
    }
}
