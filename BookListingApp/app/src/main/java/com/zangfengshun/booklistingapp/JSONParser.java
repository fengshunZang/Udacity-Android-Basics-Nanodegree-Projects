package com.zangfengshun.booklistingapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Zang on 2016-07-12.
 */
public class JSONParser {
    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";

    //constructor
    public JSONParser() {
    }

    public JSONObject getJSONFromUrl(String myurl) {
        //Making HTTP request
        try {
            Log.v("MainActivity", myurl);
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(1000);
            conn.setConnectTimeout(1000);
            //Starts the query
            conn.connect();
            is = conn.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("JSONParser", "MalformedURLException");
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.e("JSONParser", "ProtocalException");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line + "\n";
                sb.append(line);
            }
            is.close();
            Log.v("MainActivity", "get json string");
            json = sb.toString().trim();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;

    }
}
