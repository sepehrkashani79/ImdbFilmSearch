package com.example.imdbfilmsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class filmRecyclerAdapter extends RecyclerView.Adapter<filmRecyclerAdapter.filmRecyclerHolder> {
    ArrayList<film> filmList=null;

//    public filmRecyclerAdapter(List<film> filmList) {
//        this.filmList = filmList;
//    }

    @NonNull
    @Override
    public filmRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item,parent,false);
        filmRecyclerHolder holder =new filmRecyclerHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull filmRecyclerHolder holder, int position) {
        holder.title.setText(filmList.get(position).name);
//        holder.pic.setImageResource(filmList.get(position).poster);
        Picasso.get().load(filmList.get(position).poster).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    class filmRecyclerHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        public filmRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.movieTitle);
            pic= itemView.findViewById(R.id.moviePoster);

        }
    }

}
