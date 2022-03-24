package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddItemtoAddtoListPage extends AppCompatActivity implements AddToListAdapter.OnItemClickListener {

    private Button submitButton;

    private RecyclerView mRecyclerView;
    private AddToListAdapter mAddToListAdapter;
    private ArrayList<add_to_list_item> mItemList;
    private ArrayList<JSONObject> mFoodObjects;
    private RequestQueue mRequestQueue;

    public String passedCategory = "";
    public int passedStoreId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_itemto_addto_list_page);

        Intent intent = getIntent();
        passedCategory = intent.getStringExtra("storeCategory");
        passedStoreId = intent.getIntExtra("storeid",0);

        mRecyclerView = findViewById(R.id.adding_item_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemList = new ArrayList<>();
        mFoodObjects = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

        submitButton = (Button) findViewById(R.id.SubmitItemToListButton);



    }

    private void parseJSON(){
        String url = "http://10.0.2.2:3000/items/" + passedStoreId + "/" + passedCategory;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject foodObject = response.getJSONObject(i);

                                String foodName = foodObject.getString("name");
                                int aisleNumber = foodObject.getInt("aisle_id");
                                int shelfNumber = foodObject.getInt("shelf_id");

                                mItemList.add(new add_to_list_item(foodName, aisleNumber, shelfNumber));
                                mFoodObjects.add(foodObject);
                            }

                            mAddToListAdapter = new AddToListAdapter(AddItemtoAddtoListPage.this, mItemList);
                            mRecyclerView.setAdapter(mAddToListAdapter);
                            mAddToListAdapter.setOnItemClickListener(AddItemtoAddtoListPage.this);

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

        add_to_list_item chosenFood = mItemList.get(position); //gets value of chosenfood at position and applies to a custom datatype add_to_list_item chosenFood
        String foodNameString = chosenFood.getFoodName(); //Then translates that into a usable string
        String food = "";

        try {
            for (int i = 0; i < mItemList.size(); i++) {
                if (mItemList.get(i).getFoodName().equals(foodNameString)) {
                    food = mFoodObjects.get(i).getString("id");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        int foodID = Integer.parseInt(food);




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do similar jig in the choosing store page as here,
                // need to get id of item I've chosen,
                // and then push that id to a list with parameters to userid, storeid, and date
                //For all of this, I need the position I chose

                RequestQueue queue = Volley.newRequestQueue(AddItemtoAddtoListPage.this);
                String url = "http://10.0.2.2:3000/addItem/1/38";

                StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(AddItemtoAddtoListPage.this, "Response is:" + response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddItemtoAddtoListPage.this, "user is already registered, please sign in", Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){


                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", Integer.toString(passedStoreID));
                        paramV.put("fName", firstName.getText().toString());
                        paramV.put("lName", lastName.getText().toString());
                        return paramV;


                    }
                };
                queue.add(stringRequest);


            }
        });

    }
}