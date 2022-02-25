package com.example.marketmapping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ExampleStoreName> mExampleList;

    public Adapter(Context context, ArrayList<ExampleStoreName> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExampleStoreName currentStoreName = mExampleList.get(position);

        String storeName = currentStoreName.getStoreName();

        holder.mTextViewStoreName.setText(storeName);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewStoreName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewStoreName = itemView.findViewById(R.id.text_view_storename);
        }
    }
}
