package com.example.freeeducation00;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Technology> myData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList<Technology> data) {
        this.mInflater = LayoutInflater.from(context);
        this.myData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Technology technology = myData.get(position);
        holder.description.setText(technology.getDescription());
        holder.title.setText(technology.getTitle());
        holder.image.setImageResource(technology.getImage());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return myData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView description;
        TextView title;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.rowItemDescription);
            title = itemView.findViewById(R.id.rowItemTitle);
            image = itemView.findViewById(R.id.rowItemImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Technology getItem(int id) {
        return myData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
