package com.example.freeeducation00;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private List<Task> myData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyTaskRecyclerViewAdapter(Context context, ArrayList<Task> data) {
        this.mInflater = LayoutInflater.from(context);
        this.myData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.task_row_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = myData.get(position);
        holder.description.setText(String.valueOf(task.getDescription()));
        holder.title.setText(String.valueOf(task.getTitle()));
        holder.websites.setText(String.valueOf(task.getWebsites()));
        holder.videos.setText(String.valueOf(task.getVideos()));

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
        TextView websites;
        TextView videos;

        ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.taskDescription);
            title = itemView.findViewById(R.id.taskTitle);
            websites = itemView.findViewById(R.id.taskWebsites);
            videos= itemView.findViewById(R.id.taskVideos);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Task getItem(int id) {
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
