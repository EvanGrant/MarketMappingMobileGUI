package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomePage extends AppCompatActivity {
    private Button button;
    private Button buttonViewListofUser;
    private Button startShoppingButton;
    String urlForGettingUserID = "http://10.0.2.2:3000/users/";
    public ArrayList<JSONObject> mUserIds;

    public String passedEmail = "";

    public int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mUserIds = new ArrayList<>();

        Intent intent = getIntent();
        passedEmail = intent.getStringExtra("passedEmail");

        parseJSON();

        //locally defined variables for testing purposes
        String[] testArray;
        testArray = new String[1000];

        testArray = parseJSON_Array(3, 38, "List 2");

        button = (Button) findViewById(R.id.startShoppingButtonHomePage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityChoosingStorePage(); }
        });

        buttonViewListofUser = (Button) findViewById(R.id.viewListsofUserButton);
        buttonViewListofUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityShowStoresofUserListsPage(); }
        });

        startShoppingButton = (Button) findViewById(R.id.StartShoppingButton);
        startShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityShoppingChoosingListPage(); }
        });


    }


    public void openActivityChoosingStorePage()
    {
        Intent intent = new Intent(this, ChoosingStoreActivity.class);

        intent.putExtra("passedUserID", userID);

        startActivity(intent);
    }

    public void openActivityShowStoresofUserListsPage()
    {
        Intent intent = new Intent(this, ShowStoresOfUserListsPage.class);

        intent.putExtra("passedUserID", userID);

        startActivity(intent);
    }

    //select existing list button
    public void openActivityShoppingChoosingListPage() {
        Intent intent = new Intent(this, ShoppingChoosingListPage.class);

        startActivity(intent);
    }

    private void parseJSON() //this function gets the userid from the database specific to the email we put in for the sign in page
    {
        RequestQueue queue = Volley.newRequestQueue(HomePage.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlForGettingUserID, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject userObject = response.getJSONObject(i);

                                mUserIds.add(userObject);
                            }

                            for (int i = 0; i < mUserIds.size(); i++) {
                                if (mUserIds.get(i).getString("email").equals(passedEmail))
                                {
                                    userID = mUserIds.get(i).getInt("id");
                                }
                            }
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonArrayRequest);

    }

    //This is going to be used for running the list
    public String[] parseJSON_Array(int storeId, int userId, String listName) {

        String[] arr; //initialize an array
        arr = new String[1000];

        int section_id = 0;

        String url = "http://10.0.2.2:3000/findList/3/38/List 2/";

        RequestQueue queue = Volley.newRequestQueue(HomePage.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject storeObject = response.getJSONObject(i);

                                String aisle = storeObject.getString("aisleId");
                                String section = storeObject.getString("sectionId");
                                String name = storeObject.getString("name");
                                String section_id = storeObject.getString("section_id");

                                //int section_id_int = Integer.parseInt(section_id);
                                int section_id_int = i;

                                if(arr[section_id_int] == null) {
                                    arr[section_id_int] = "go to aisle " + aisle + ", section " + section + " for " + name; //add the aisle and section number and the name
                                }else {

                                    arr[section_id_int] += " and " + name; //add the name of the item
                                }
                            }

                            doStuff(arr);

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

        queue.add(jsonArrayRequest);

        return arr;

    }

    //do everything that pertains to the array arr here
    private void doStuff(String[] arr) {

        Toast.makeText(HomePage.this, "Response: " + arr[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(HomePage.this, "Response: " + arr[1], Toast.LENGTH_SHORT).show();
        Toast.makeText(HomePage.this, "Response: " + arr[2], Toast.LENGTH_SHORT).show();


    }


}