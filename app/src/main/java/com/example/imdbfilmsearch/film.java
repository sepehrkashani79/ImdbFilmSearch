package com.example.imdbfilmsearch;
import android.util.Log;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class film {
    public film(String name, String poster) {
        this.name = name;
        this.poster = poster;
    }

    static String searchResult;
    String name;
    int rate;
    String description;
    String poster;

    public static List<film> searchByName(String title) {
        final String addres="http://www.omdbapi.com/?apikey=70ad462a&type=movie&s="+title;
//        String searchResult;
        ArrayList<film> results =new ArrayList<film>();
        findInImdb(addres);
        Log.d("mytag",searchResult);
        try{
            JSONObject all = new JSONObject(film.searchResult);
            JSONArray filmArray = new JSONArray(all.getString("search"));
            for (int i = 0; i < filmArray.length(); i++) {
                JSONObject jsonobject = filmArray.getJSONObject(i);
                String name = jsonobject.getString("title");
                String pic=jsonobject.getString("poster");
                results.add(new film(name,pic));
                Log.d("mytag",name);


            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return results;

    }
    public static void findInImdb(final String addres){
        AsyncHttpClient client =new AsyncHttpClient();
        client.get(addres,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                film.searchResult=response.toString();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //String result="";
//                try {
//
//                    URL obj = new URL(addres);
//
//                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//                    con.setRequestMethod("GET");
//                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//                    int responseCode = con.getResponseCode();
//
//                    if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                        BufferedReader in = new BufferedReader(new InputStreamReader(
//                                con.getInputStream()));
//
//                        String inputLine;
//                        StringBuffer response = new StringBuffer();
//                        while ((inputLine = in.readLine()) != null) {
//                            response.append(inputLine);
//                        }
//
//
//                        film.searchResult=response.toString();
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //return result;
//
//            }
//        }).start();

    }
}
