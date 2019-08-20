package com.example.imdbfilmsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search =findViewById(R.id.btnSearch);
        final EditText txtFilmName=findViewById(R.id.txtFilmName);
        final RecyclerView filmList=findViewById(R.id.filmListRecycler);
        final filmRecyclerAdapter adapter=new filmRecyclerAdapter();
        filmList.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mytag",txtFilmName.getText().toString());
                film.searchByName(txtFilmName.getText().toString(),filmList,adapter);
            }
        });
    }
}
