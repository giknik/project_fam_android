package com.example.famapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowNotes extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView notes;
    private final String url = "https://fam-is.azurewebsites.net/api/v1/Notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        configureBackButton2();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        notes = (TextView) findViewById(R.id.notes);
    }

    private void configureBackButton2() {
        Button goBack2 = (Button) findViewById(R.id.back2);
        goBack2.setOnClickListener(v -> finish());
    }

    public  void showNotes(View view){
        System.out.println("showing");
        if (view != null){
            JsonArrayRequest request = new JsonArrayRequest(url, jsonArrayListener, errorListener)
            {
                @Override
                public Map<String,String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("ApiKey", "GapyNicky420");
                    return params;
                }
            };
            requestQueue.add(request);
        }
    }

    private final Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response){
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < response.length(); i++){
                try {
                    JSONObject object =response.getJSONObject(i);
                    String id = object.getString("noteID");
                    String title = object.getString("noteTitle");
                    String text = object.getString("noteText");
                    String color = object.getString("noteHexColor");

                    text = text.replaceAll("(\r\n|\n)", "<br>");

                    data.add("<font color=" + color + ">" + id + "<br><b>" + title + "</b><br>" + text + "</font>");

                } catch (JSONException e){
                    e.printStackTrace();
                    return;

                }
            }

            notes.setText("");

            StringBuilder allRows = new StringBuilder();

            for (String row: data){
                allRows.append(row).append("<br><br>");
            }
            notes.setText(Html.fromHtml(allRows.toString()));
        }
    };

    private final Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("REST error", error.getMessage());
        }
    };

    public static final String EXTRA_MESSAGE = "com.example.famapp.MESSAGE";
}

