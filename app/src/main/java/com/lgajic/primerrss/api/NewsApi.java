package com.lgajic.primerrss.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsApi {
    public static final String RSS_URL_JSON = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.kurir.rs%2Frss";
    private static final String TAG = "NewsApi";

    public static void getJSON(String url, final ReadDataHandler rdh) {
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String response = "";

                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String row;

                    while ((row = reader.readLine()) != null) {
                        response += row + "\n";
                    }

                    reader.close();
                    connection.disconnect();

                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                    response = "[]";
                }

                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                // super.onPostExecute(s);
                rdh.setJson(response);
                rdh.sendEmptyMessage(0);
            }
        };

        task.execute(url);
    }
}
