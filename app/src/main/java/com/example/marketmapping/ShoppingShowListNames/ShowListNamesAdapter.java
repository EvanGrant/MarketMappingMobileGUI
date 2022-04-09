package com.example.marketmapping.ShoppingShowListNames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketmapping.R;

import java.util.ArrayList;

public class ShowListNamesAdapter extends RecyclerView.Adapter<ShowListNamesAdapter.ShowListNamesViewHolder> {
    private Context mContext;
    private ArrayList<ShowListNamesItem> mShowListNamesList;

    public ShowListNamesAdapter(Context context, ArrayList<ShowListNamesItem> showListNamesList) {
        mContext = context;
        mShowListNamesList = showListNamesList;
    }

    @NonNull
    @Override
    public ShowListNamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.show_list_names_item, parent, false);
        return new ShowListNamesViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ShowListNamesViewHolder holder, int position) {
        ShowListNamesItem currentItem = mShowListNamesList.get(position);

        String ListNamesShowList = currentItem.getListNamesShowList();
        String StoreNameShowList = currentItem.getStoreNameShowList();

        holder.mTextViewListNames.setText(ListNamesShowList);
        holder.mTextViewStoreName.setText(StoreNameShowList);
    }

    @Override
    public int getItemCount() {
        return mShowListNamesList.size();
    }


    public class ShowListNamesViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewListNames;
        public TextView mTextViewStoreName;

        public ShowListNamesViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewListNames = itemView.findViewById(R.id.text_view_list_name_from_show_list_name);
            mTextViewStoreName = itemView.findViewById(R.id.text_view_store_name_from_show_list_name);
        }
    }

}
