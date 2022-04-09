package com.example.marketmapping.EditListShowContentsOfList;
//THIS IS FOR EDIT LIST ROUTE, GETS CREATED WHEN CLICKING ON A STORE IN EDIT LIST BUTTON IN HOME PAGE
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketmapping.R;

import java.util.ArrayList;

public class ShowContentsOfListAdapter extends RecyclerView.Adapter<ShowContentsOfListAdapter.ShowContentsOfListViewHolder> {
private Context mContext;
private ArrayList<ShowContentsOfListItem> mShowContentsOfListList;

    public ShowContentsOfListAdapter(Context context, ArrayList<ShowContentsOfListItem> showContentsOfListList) {
        mContext = context;
        mShowContentsOfListList = showContentsOfListList;
    }

    @NonNull
    @Override
    public ShowContentsOfListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.show_contents_of_list_item, parent, false);
        return new ShowContentsOfListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowContentsOfListViewHolder holder, int position) {
        ShowContentsOfListItem currentItem = mShowContentsOfListList.get(position);

        String FoodName = currentItem.getFoodName();
        int AisleNumber = currentItem.getAisleNumber();
        int ShelfNumber = currentItem.getShelfNumber();

        holder.mTextViewAisleNumber.setText("Aisle: " + AisleNumber);
        holder.mTextViewShelfNumber.setText("Section: " + ShelfNumber);
        holder.mTextViewFoodName.setText(FoodName);
    }

    @Override
    public int getItemCount() {
        return mShowContentsOfListList.size();
    }


    public class ShowContentsOfListViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewFoodName;
        public TextView mTextViewShelfNumber;
        public TextView mTextViewAisleNumber;


        public ShowContentsOfListViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewFoodName = itemView.findViewById(R.id.text_view_food_name_contents);
            mTextViewShelfNumber = itemView.findViewById(R.id.text_view_section_of_item_contents);
            mTextViewAisleNumber = itemView.findViewById(R.id.text_view_aisle_of_item_contents);

        }
    }



}
