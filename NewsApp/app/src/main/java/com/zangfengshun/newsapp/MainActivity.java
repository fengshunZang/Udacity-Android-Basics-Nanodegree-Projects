package com.zangfengshun.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_RESPONSE = "response";
    private static final String TAG_RESULTS = "results";
    private static final String TAG_WEBTITLE = "webTitle";
    private static final String TAG_WEBPUBLICATIONDATE = "webPublicationDate";
    private static final String TAG_WEBURL = "webUrl";
    private static final String KEYWORD_POLITICS = "politics";
    private static final String KEYWORD_BUSINESS = "business";
    private static final String KEYWORD_TECH = "technology";
    private static final String KEYWORD_HEALTH = "health";
    private static final String SEARCH_URL_PREFIX = "http://content.guardianapis.com/search?q=";
    private static final String SEARCH_URL_SUFFIX = "&api-key=test";
    private static final String KEYWORD_ENTERTAINMENT = "entertainment";
    private String myUrl;
    private ConnectivityManager mConnMgr;
    private NetworkInfo mNetworkInfo;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnMgr.getActiveNetworkInfo();
        textView = (TextView) findViewById(R.id.textView);

    }

    public void getNews(View v) {
        switch (v.getId()) {
            case R.id.button1:
                myUrl = SEARCH_URL_PREFIX + KEYWORD_POLITICS + SEARCH_URL_SUFFIX;
                break;
            case R.id.button2:
                myUrl = SEARCH_URL_PREFIX + KEYWORD_BUSINESS + SEARCH_URL_SUFFIX;
                break;
            case R.id.button3:
                myUrl = SEARCH_URL_PREFIX + KEYWORD_TECH + SEARCH_URL_SUFFIX;
                break;
            case R.id.button4:
                myUrl = SEARCH_URL_PREFIX + KEYWORD_HEALTH + SEARCH_URL_SUFFIX;
                break;
            case R.id.button5:
                myUrl = SEARCH_URL_PREFIX + KEYWORD_ENTERTAINMENT + SEARCH_URL_SUFFIX;
                break;
        }
        Log.v("MainActivity", "button clicked, and url is: " + myUrl);
        if (mConnMgr != null && mNetworkInfo.isConnected()) {
            new SearchNews().execute();
        } else {
            textView.setText(getResources().getText(R.string.No_internet_connection));
        }
    }

    private class SearchNews extends AsyncTask<String, String, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser jsonParser = new JSONParser();
            return jsonParser.getJSONObjectFromUrl(myUrl);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            ArrayList<NewsItem> news = new ArrayList<>();
            try {
                JSONObject jsonObject1 = jsonObject.getJSONObject(TAG_RESPONSE);
                JSONArray jsonArray = jsonObject1.getJSONArray(TAG_RESULTS);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject currentJsonObj = jsonArray.getJSONObject(i);
                    String title = currentJsonObj.optString(TAG_WEBTITLE);
                    Log.v("MainActivity", "title");

                    String publicationDate = currentJsonObj.optString(TAG_WEBPUBLICATIONDATE);
                    String webUrl = currentJsonObj.optString(TAG_WEBURL);
                    Log.v("MainActivity", "publicationDate");

                    NewsItem item = new NewsItem(title, publicationDate, webUrl);
                    news.add(item);
                }
            } catch (JSONException e) {
                Log.v("MainActivity", "JSONException occurs when loading data from jsonObjects");
            }
            Intent intent = new Intent(MainActivity.this, NewsDisplayActivity.class);
            intent.putExtra("news", news);
            startActivity(intent);
        }
    }
}
