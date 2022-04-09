package com.example.marketmapping.ShoppingShowListNames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketmapping.R;
import com.example.marketmapping.ShoppingShowingListContents.ShoppingShowingListContentsItem;
import com.example.marketmapping.ShoppingShowingListContents.ShoppingShowingListContentsPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowListNamesPage extends AppCompatActivity implements ShowListNamesAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ShowListNamesAdapter mShowListNamesAdapter;
    private ArrayList<ShowListNamesItem> mShowListNamesList;
    private RequestQueue mRequestQueue;

    public int passedUserID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_names_page);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);

        mRecyclerView = findViewById(R.id.show_list_names_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShowListNamesList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON(){
        String url = "http://10.0.2.2:3000/getListNames/" + passedUserID + "/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject listObject = response.getJSONObject(i);

                                String listName = listObject.getString("listName");
                                String storeName = listObject.getString("name");


                                mShowListNamesList.add(new ShowListNamesItem(listName, storeName));
                            }

                            mShowListNamesAdapter = new ShowListNamesAdapter(ShowListNamesPage.this, mShowListNamesList);
                            mRecyclerView.setAdapter(mShowListNamesAdapter);
                            mShowListNamesAdapter.setOnItemClickListener(ShowListNamesPage.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onItemClick(View view, int position) {

        ShowListNamesItem chosenList = (mShowListNamesList.get(position)); //grabs the list chosen in the onitemclick through positon in the arraylist mShowListNamesList
        String chosenListString = chosenList.getListNamesShowList(); // converts to String to use for concatenation for next page

        Intent itemIntent = new Intent(this, ShoppingShowingListContentsPage.class);
        ShowListNamesItem clickedItem = mShowListNamesList.get(position);

        itemIntent.putExtra("passedListName", chosenListString);


        startActivity(itemIntent);

    }
}