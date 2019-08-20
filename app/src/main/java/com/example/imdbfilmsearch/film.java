package com.example.imdbfilmsearch;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

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
    public film(String name) {
        this.name = name;
    }

    public film(String name, String poster) {
        this.name = name;
        this.poster = poster;
    }

    static String searchResult;
    String name;
    int rate;
    String description;
    String poster;
    static ArrayList<film> results=new ArrayList<>();

    public static void searchByName(String title , final RecyclerView recycler, final filmRecyclerAdapter adapter) {
        final String addres="http://www.omdbapi.com/?apikey=70ad462a&s="+title;
        results.clear();
        AsyncHttpClient client =new AsyncHttpClient();
        client.get(addres,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());
                try{
                    JSONObject all = new JSONObject(response.toString());
                    JSONArray filmArray = new JSONArray(all.getString("Search"));


                    for (int i = 0; i < filmArray.length(); i++) {
                        JSONObject jsonobject = filmArray.getJSONObject(i);
                        String name = jsonobject.getString("Title");
                        String pic = jsonobject.getString("Poster");
                        results.add(new film(name,pic));
                        Log.d("myTag",name);
                    }
                    adapter.filmList=results;
                    recycler.setAdapter(adapter);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });












        //return results;

    }

}
