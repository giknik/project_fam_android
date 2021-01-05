package com.example.famapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;
import android.view.View;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddNote extends AppCompatActivity {

    private TextView status;
    private EditText name;
    private EditText surname;

    private RequestQueue requestQueue;
    private final String url = "https://fam-is.azurewebsites.net/api/v1/Notes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        configureBackButton1();

        name = (EditText) findViewById(R.id.formTitle);
        surname = (EditText) findViewById(R.id.formText);
        status = (TextView) findViewById(R.id.status);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }

    private void configureBackButton1() {
        Button goBack1 = (Button) findViewById(R.id.back1);
        goBack1.setOnClickListener(v -> finish());
    }

    public void addNote(View view){
        this.status.setText(String.format("Posting to %s", url));
        String[] colors = new String[]{"#d60000", "#039be5", "#7986cb", "#33b679", "#0b8043",
                "#8e24aa", "#e67c73", "#f6c026", "#f5511d", "#616161", "#3f51b5"};

        int idx = new Random().nextInt(colors.length);
        String random = (colors[idx]);

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("noteHexColor", random);
            jsonBody.put("noteTitle", name.getText());
            jsonBody.put("noteText", surname.getText());
            jsonBody.put("noteOwnerID", "123");
            jsonBody.put("familyID", "1");

            final String mRequestBody = jsonBody.toString();

            status.setText(mRequestBody);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            }
            ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        status.setText(responseString);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                public Map<String,String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("ApiKey", "GapyNicky420");
                    params.put("Content-Type", "application/json");
                    return params;
                }

            };

            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
