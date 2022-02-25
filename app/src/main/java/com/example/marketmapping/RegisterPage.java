package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

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

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(RegisterPage.this, "Response is:" + response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterPage.this, "user is already registered, please sign in", Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", emailBoxRegister.getText().toString());
                        paramV.put("fName", firstName.getText().toString());
                        paramV.put("lName", lastName.getText().toString());
                        paramV.put("PWord", passwordBoxRegister.getText().toString());
                        return paramV;

                    }
                };
                queue.add(stringRequest); 
            }
        });
    }



    public void openHomePage()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

}