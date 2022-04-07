

package Algo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*

public class FindSections
{
	 private String[] parseJSON(String storeId, String userId, String listName) {
		 
		 	String[] arr = null; //initialize an array
			
			int section_id = 0;
			
	        String url = "http://10.0.2.2:3000/findList/" + storeId + "/" + userId + "/ " + listName + "/";

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
	                                
	                                if(arr[section_id] == null) {
	                    				arr[section_id] = "go to aisle " + aisle + ", section " + section + "for " + name; //add the aisle and section number and the name
	                    			}else {
	                    				
	                    				arr[section_id] += " and " + name; //add the name of the item
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
	        
	        return arr;

	    }
	 
}// end FindSections



*/