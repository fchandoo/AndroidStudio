

// https://developer.android.com/guide/topics/ui/layout/recyclerview#java
// Terrible design issue
// Confusing naming -- RecyclerViewAdapter, ViewHolder, data, textView, viewGroup, etc.

//public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
//    private String[] data;
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;
//
//        public ViewHolder(View view) {
//            super(view);
//
//            // Define click listener for the ViewHolder's View
//            textView = (TextView) view.findViewById(R.id.te);
//        }
//
//        public TextView getTextView() {
//            return textView;
//        }
//    }
//
//    public RecyclerViewAdapter (String[] data){
//        this.data = data;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        // Create a new view, which defines the UI of the list item
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.recyclerview_row, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        // Get element from your dataset at this position and replace the
//        // contents of the view with that element
//        holder.textView.setText(data[position]);
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return data.length;
//    }
//}

package com.example.a10rv;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    //adapter working with holder
    private final ArrayList<String> mv_data1, mv_data2;
    Activity cv_activity;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView cv_tvmodel;
        private final TextView cv_tvOS;
        private final TextView cv_tvPrice;

        public MyViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            cv_tvmodel = view.findViewById(R.id.vv_tvmodel);
            cv_tvOS = view.findViewById(R.id.vv_tvOS);
            cv_tvPrice= view.findViewById(R.id.vv_tvPrice);
        }
    }
    public MyRecyclerViewAdapter(ArrayList data01, ArrayList<String> data02, Activity activity) {
        mv_data1 = data01;
        mv_data2= data02;
        cv_activity=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View lv_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(lv_view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.cv_tvmodel.setText(mv_data1.get(position));
        holder.cv_tvOS.setText(mv_data2.get(position));
        holder.cv_tvPrice.setText("Place Holder");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent lv_it = new Intent(cv_activity, MyListDetailsActivity.class);
                        lv_it.putExtra("OSName",
                                (String) mv_data2.get(holder.getAdapterPosition()).toString());
                        cv_activity.startActivity(lv_it);
                        cv_activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    }}); }}); }// Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mv_data1.size();
    }




}

