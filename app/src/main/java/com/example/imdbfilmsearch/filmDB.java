package com.example.imdbfilmsearch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class filmDB extends SQLiteOpenHelper {
    String dbName;
    static String db_create_query;

    public filmDB(Context context,String dbName) {
//        super(context, name, factory, version);
        super(context, "name11", null, 2);
        this.dbName = dbName;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db_create_query="CREATE TABLE "+dbName+"("
                +"_id integer primary key AUTOINCREMENT,"
                +"filmName text,"
                +"filmPosterLink text";
        sqLiteDatabase.execSQL(db_create_query);
        Log.d("myTag","database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(String filmName,String posterLink){
        String insert_query="INSERT INTO "+dbName+"(filmName, filmPosterLink)"+"VALUES('"+filmName+"','"+posterLink+"')";
        Log.d("myTag","opened writable database");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("myTag","opened writable database");
        db.execSQL(insert_query);
        Log.d("myTag","insertquery executed");
        db.close();
    }
    public void insert_list(ArrayList<film> list){
        for(int i=0;i<list.size();i++){
            insert(list.get(i).name,list.get(i).poster);
        }
    }
    public ArrayList<film> getAllFilms(){
        ArrayList<film> filmList=new ArrayList<film>();
        SQLiteDatabase db = this. getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT filmName AND filmPosterLink from " + dbName, null);
        while (cursor.moveToNext()) {
            filmList.add(new film(cursor.getString(0),cursor.getString(1)));
        }
        db.close();
        return filmList;
    }
    public void delete_db_contents(){
        String delete_query="DELETE FROM "+dbName;
        SQLiteDatabase db = this. getReadableDatabase();
        db.execSQL(delete_query);
        db.close();
    }


}
