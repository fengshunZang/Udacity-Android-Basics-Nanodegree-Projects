package com.zangfengshun.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHOR = "authors";
    private static final String TAG_ITEM = "items";
    private static final String TAG_VOLUMEINFO = "volumeInfo";

    private Button mSearch_button;
    private EditText mKeyWord;
    private TextView mText;
    private String myUrl = "";
    private ConnectivityManager mConnMgr;
    NetworkInfo mNetworkInfo;

    private static final String REQUEST_URL_PREFIX = "https://www.googleapis.com/books/v1/volumes?q=intitle:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearch_button = (Button) findViewById(R.id.search_button);
        mText = (TextView) findViewById(R.id.text);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnMgr.getActiveNetworkInfo();

        mText.setText(getResources().getText(R.string.initial_display));

        mSearch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyWord = (EditText) findViewById(R.id.edit_query);

                if (mKeyWord != null) {
                    myUrl = REQUEST_URL_PREFIX + mKeyWord.getText().toString();
                }
                if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
                    new SearchWebpageTask().execute();
                } else {
                    mText.setText(getResources().getText(R.string.No_internet));
                }
            }
        });

    }

    private class SearchWebpageTask extends AsyncTask<String, String, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            //Getting JSON from URL
            return jParser.getJSONFromUrl(myUrl);
        }

        protected void onPostExecute(JSONObject json) {
            //Get the instance of JSONArray that contains JSONObjects
            ArrayList<Item> result = new ArrayList<Item>();
            try {
                JSONArray jsonArray = json.getJSONArray(TAG_ITEM);

                //Iterate the jsonArray and add corresponding value of JSONObjects to arraylist.
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject bookInfo = (JSONObject) jsonObject.get(TAG_VOLUMEINFO);

                    String bookTitle = bookInfo.optString(TAG_TITLE);
                    if (bookTitle == "") {
                        bookTitle = "No book found";
                    }

                    String author = bookInfo.optString(TAG_AUTHOR);
                    if (author == "") {
                        author = "No author found";
                    }

                    Item item = new Item(bookTitle, author);
                    result.add(item);
                }
            } catch (JSONException e) {
                Log.e("MainActivity", "JSONException");
                e.printStackTrace();
            } finally {

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        }
    }
}
