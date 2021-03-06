package com.example.marketmapping.CreatListAddItemtoAddtoList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketmapping.R;

import java.util.ArrayList;

public class AddToListAdapter extends RecyclerView.Adapter<AddToListAdapter.AddToListViewHolder> {
private Context mContext;
private ArrayList<add_to_list_item> mItemList;

private OnItemClickListener mListener;

public interface OnItemClickListener {
    void onItemClick(View view, int position);
}

public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
}

int selectedPos = RecyclerView.NO_POSITION; //line for highlighting selected item in recyclerview

public AddToListAdapter(Context context, ArrayList<add_to_list_item> ItemList) {
    mContext = context;
    mItemList = ItemList;
}

    @NonNull
    @Override
    public AddToListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.add_to_list_item, parent, false);
        return new AddToListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToListViewHolder holder, int position) {
        add_to_list_item currentItem = mItemList.get(position);

        String FoodName = currentItem.getFoodName();
        int ShelfNumber = currentItem.getShelfNumber();
        int AisleNumber = currentItem.getAisleNumber();

        holder.mFoodName.setText(FoodName);
        holder.mShelfNumber.setText("Shelf: " + ShelfNumber);
        holder.mAisleNumber.setText("Aisle: " + AisleNumber);

        holder.itemView.setSelected(selectedPos == position); //line for highlighting selected item in recyclerview


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public class AddToListViewHolder extends RecyclerView.ViewHolder {
        public TextView mFoodName;
        public TextView mShelfNumber;
        public TextView mAisleNumber;

        public AddToListViewHolder(@NonNull View itemView) {
            super(itemView);
            mFoodName = itemView.findViewById(R.id.text_view_food_name);
            mShelfNumber = itemView.findViewById(R.id.text_view_shelf_of_item);
            mAisleNumber = itemView.findViewById(R.id.text_view_aisle_of_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(v, position);
                        }
                    }

                    notifyItemChanged(selectedPos);
                    selectedPos = getLayoutPosition();
                    notifyItemChanged(selectedPos);

                }
            });

        }
    }

}
