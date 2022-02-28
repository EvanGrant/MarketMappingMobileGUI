package com.example.marketmapping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context mContext;
    private ArrayList<ExampleCategory> mExampleList;

    public CategoryAdapter(Context context, ArrayList<ExampleCategory> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        ExampleCategory currentCategory = mExampleList.get(position);

        String categoryName = currentCategory.getCategoryName();

        holder.mTextViewCategory.setText(categoryName);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewCategory = itemView.findViewById(R.id.text_view_storename);

        }
    }
}
