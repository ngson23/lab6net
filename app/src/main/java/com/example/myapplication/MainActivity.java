package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    TextView tv;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        tv = findViewById(R.id.tv);

        btn1.setOnClickListener((view) -> {
            getStringVolley();
        });


        btn2.setOnClickListener((view) -> {
            getJSON_ObjectOfArray();
        });

    }
    String kq;
    private void getJSON_ObjectOfArray() {
        //1. tạo queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //2. truyền url

        String url = "https://batdongsanabc.000webhostapp.com/mob403lab6/array_json_new.json";
        //3. tạo request -> (xác định loại request)
        // trường hợp này là arrayRequest
        //JsonArrayRequest(url, thành công, thất bại)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        kq = "";
                        for (int i=0; i<response.length(); i++){
                            try {
                                JSONObject person = response.getJSONObject(i);
                                String id = person.getString("id");
                                String name = person.getString("name");
                                String email = person.getString("email");
                                JSONObject phone = person.getJSONObject("phone");
                                String mobile = phone.getString("mobile");
                                String home = phone.getString("home");

                                kq += "id: " +id + "\n\n";
                                kq += "name: " +name + "\n\n";
                                kq += "email: " +email + "\n\n";
                                kq += "moblie: " +mobile + "\n\n";
                                kq += "home: " +home + "\n\n";

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tv.setText(kq);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText(error.getMessage());
                    }
                });
        //4. xử lý request
        requestQueue.add(jsonArrayRequest);
    }

    // đọc chuỗi từ trang google.com
    private void getStringVolley() {
        //b1: tạo queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //b2: tạo url
        String url = "https://www.google.com";
        //b3: tạo request
        //StringRequest(phương thức,url , thành công, thất bại)
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv.setText("Ket qua: " + response.substring(0, 1000));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText(error.getMessage());
                    }
                });
        requestQueue.add(stringRequest);

    }
}