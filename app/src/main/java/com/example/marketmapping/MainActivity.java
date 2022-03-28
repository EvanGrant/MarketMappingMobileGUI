package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String passedEmail = "";
    String urlForGettingUserID = "http://10.0.2.2:3000/users/";
    public ArrayList<JSONObject> mUserIds;

    private RequestQueue mRequestQueue;

    public int userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defined the IDs in the xml file
        EditText emailbox = findViewById(R.id.emailBoxActivityMain);
        EditText passwordbox = findViewById(R.id.passwordBoxActivityMain);
        Button SubmitButtonActivityMain = findViewById(R.id.SubmitButtonActivityMain);
        Button registerButtonActivityMain = findViewById(R.id.registerButtonActivityMain);

        mRequestQueue = Volley.newRequestQueue(this);

        mUserIds = new ArrayList<>();


        SubmitButtonActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailbox.getText().toString(); //local variables for concatenation to url
                String password = passwordbox.getText().toString(); //Have to define and assign these in the SubmitButtonActivity, doing that above resulted in parsing errors

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://10.0.2.2:3000/users/" + email + "/" + password + "/"; //concatenation from the edittexts on the page, to check for the specific user input

                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Find line of code to see response from RestAPI
                        //With that, find out what it is, then see where you're going wrong
                        Toast.makeText(MainActivity.this, "Welcome User", Toast.LENGTH_SHORT).show();

                        passedEmail = email;

                        openHomePage();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(MainActivity.this, "Volley Error " + error.getMessage(), Toast.LENGTH_SHORT).show(); //hopefully this prints out error message
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(MainActivity.this, "Authentication Error " + error.getMessage(), Toast.LENGTH_SHORT).show(); //hopefully this prints out error message
                        } else if (error instanceof ServerError) {
                            Toast.makeText(MainActivity.this, "Server Error " + error.getMessage(), Toast.LENGTH_SHORT).show(); //hopefully this prints out error message
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(MainActivity.this, "Network Error " + error.getMessage(), Toast.LENGTH_SHORT).show(); //hopefully this prints out error message
                        } else if (error instanceof ParseError) {
                            Toast.makeText(MainActivity.this, "Parse Error " + error.getMessage(), Toast.LENGTH_SHORT).show(); //hopefully this prints out error message
                        }
                    }
                });

                queue.add(request);

            }




        });

        registerButtonActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {




                openRegisterPage();

            }
        });
    }

    public void openHomePage()
    {
        Intent intent = new Intent(this, HomePage.class);


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

        intent.putExtra("passedUserID", userID);//FOR SOME REASON NOT PASSING AS THE CORRECT USERID
        intent.putExtra("passedEmail", passedEmail);



        startActivity(intent);
    }

    public void openRegisterPage()
    {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

}


