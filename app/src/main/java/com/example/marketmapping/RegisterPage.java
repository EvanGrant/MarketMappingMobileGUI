package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //Defining Variables

        EditText firstName = findViewById(R.id.firstNameRegister);
        EditText lastName = findViewById(R.id.lastNameRegister);
        EditText emailBoxRegister = findViewById(R.id.emailBoxRegister);
        EditText passwordBoxRegister = findViewById(R.id.passwordBoxRegister);
        EditText confirmPasswordBoxRegister = findViewById(R.id.confirmPasswordBoxRegister);
        Button SubmitButtonRegister = findViewById(R.id.SubmitButtonRegister);

        SubmitButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(RegisterPage.this);
                String url = "http://10.0.2.2:3000/users/";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
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
                        //Then if both email and password inserted from user is equal to database, signs in
                        if (emailBoxRegister.getText().toString().equals(email) && passwordBoxRegister.getText().toString().equals(pWord))
                        {
                            Toast.makeText(RegisterPage.this, "Signed In", Toast.LENGTH_SHORT).show();

                            openHomePage();

                        }
                        else
                        {
                            Toast.makeText(RegisterPage.this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterPage.this, "Could Not Register, Server is down", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);






            }
        });
    }

    public void openHomePage()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

}