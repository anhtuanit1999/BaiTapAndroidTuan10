package com.example.baitapandroidtuan10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList nameList;
    private ListView lv;
    String url = "https://60ab1b335a4de40017cc9577.mockapi.io/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listV);
        nameList = new ArrayList<>();
        String getAll = url + "person";
        getData(getAll);
//        lv.setAdapter(new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, nameList));
    }

    private void getData(String url){
        JsonArrayRequest request = new JsonArrayRequest(url ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = (JSONObject) response.get(i);
                        nameList.add(obj.getString("name"));
//                        Toast.makeText(MainActivity.this, nameList.get(i).toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lv.setAdapter(new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, nameList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}