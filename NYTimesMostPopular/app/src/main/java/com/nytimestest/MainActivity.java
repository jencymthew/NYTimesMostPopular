package com.nytimestest;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nytimestest.Adapter.RvAdapter;
import com.nytimestest.Common.Const;
import com.nytimestest.Data.Rvdata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    ArrayList<Rvdata> proSearch = new ArrayList<Rvdata>();

    RecyclerView recyclerView;
    RvAdapter rvAdapter;

    String request_url = Const.base_url+"all-sections/7.json?api-key=9fc2c4900af748229841e04e43b4fccf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.most_popular);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.header_layout);
        View view =getSupportActionBar().getCustomView();
        recyclerView = (RecyclerView) findViewById(R.id.rv_techsolpoint);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        proSearch = new ArrayList<>();
        makeJsonArrayRequest();
    }
    /**
     * Method to make json array request where response starts with [
     * */
    private void makeJsonArrayRequest() {

//Json Object
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                request_url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String results = response.getString("results");
                    Log.d("results", results);
                    //parsing Json Array
                    JSONArray json_array = new JSONArray(results);
                    System.out.println("json_array"+json_array);

                    for (int i = 0; i < json_array.length(); i++) {
                        JSONObject json_obj = json_array.getJSONObject(i);
                        String title = json_obj.getString("title");
                        String byline = json_obj.getString("byline");
                        String source = json_obj.getString("abstract");
                        String published_date = json_obj.getString("published_date");
                        Log.d("title", title);
                        Log.d("byline", byline);
                        Log.d("source", source);
                        Log.d("published_date", published_date);
                        Rvdata rvdata=new Rvdata(title,byline,source,published_date);
                        proSearch.add(rvdata);
                        Log.d("proSearch", proSearch.toString());
                    }
                    rvAdapter = new RvAdapter(getApplicationContext(), proSearch);
                    recyclerView.setAdapter(rvAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("VolleyReeor", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }


}


