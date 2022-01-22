package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defined the IDs in the xml file
        EditText emailbox = findViewById(R.id.emailBoxActivityMain);
        EditText passwordbox = findViewById(R.id.passwordBoxActivityMain);
        Button SubmitButtonActivityMain = findViewById(R.id.SubmitButtonActivityMain);




        SubmitButtonActivityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://10.0.2.2:3000/users/";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String email = ""; //define local variables to use for try catch down below
                        String pWord = "";

                        try {
                            JSONObject LogIn = response.getJSONObject(0);
                            email = LogIn.getString("email"); //Set email and pWord to the Volley info I get
                            pWord = LogIn.getString("pWord");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //This converts emailbox from xml to string to make sure we can compare string to string, and not edittext to string
                        if (emailbox.getText().toString().equals(email) && passwordbox.getText().toString().equals(pWord))
                        {
                            Toast.makeText(MainActivity.this, "They're Equal!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "They're Not Equal. Something is Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);






            }
        });
    }
}