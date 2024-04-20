package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    ArrayList<Mountain> items = new ArrayList<>();

    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain item) {
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);


        new JsonTask(this).execute(JSON_URL );
    }

    @Override
    public void onPostExecute(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            ArrayList<Mountain> newMountains = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.optString("name", "Unknown");
                String location = jsonObject.optString("location", "No location provided");
                int size = jsonObject.optInt("size", 0);


                JSONObject auxDataObject = jsonObject.getJSONObject("auxdata");
                String imageLoc = auxDataObject.optString("img","");

                Mountain mountain = new Mountain(name, location, size, imageLoc);
                newMountains.add(mountain);
                Log.d("Mountains", mountain.toString());
            }


            items.addAll(newMountains);
            adapter.notifyDataSetChanged();


        } catch (JSONException e) {
            Log.e("brom", "E:" + e.getMessage());
        }

    }

}
