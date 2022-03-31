package com.example.marketmapping;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserGroceryListForStoreAdapter extends RecyclerView.Adapter<UserGroceryListForStoreAdapter.UserGroceryListForStoreViewHolder> {
    private Context mContext;
    private ArrayList<UserGroceryListForStoreItem> mUserGroceryListForStoreItemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;
    }

    public UserGroceryListForStoreAdapter(Context context, ArrayList<UserGroceryListForStoreItem> userGroceryListForStoreItemList) {
        mContext = context;
        mUserGroceryListForStoreItemList = userGroceryListForStoreItemList;
    }

    @NonNull
    @Override
    public UserGroceryListForStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_grocery_list_for_store_item, parent, false);
        return new UserGroceryListForStoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserGroceryListForStoreViewHolder holder, int position) {
        UserGroceryListForStoreItem currentItem = mUserGroceryListForStoreItemList.get(position);

        String storeName = currentItem.getStoreName();
        String address = currentItem.getAddress();

        holder.mTextViewStoreName.setText(storeName);
        holder.mTextViewAddress.setText("address: " + address);

    }

    @Override
    public int getItemCount() {
        return mUserGroceryListForStoreItemList.size();
    }

    public class UserGroceryListForStoreViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewStoreName;
        public TextView mTextViewAddress;


        public UserGroceryListForStoreViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewStoreName = itemView.findViewById(R.id.text_view_store_from_list_name);
            mTextViewAddress = itemView.findViewById(R.id.text_view_address_of_store);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
