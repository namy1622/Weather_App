//package com.example.app_weather;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class Next_SevenDay extends AppCompatActivity {
//
//    String tenTP = "";
//
//    ImageView imageBack;
//    TextView txtName;
//    ListView lv;
//
//    CustomAdapter customAdapter;
//    ArrayList<ThoiTiet> arr_thoiTiet;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_next_seven_day);
//
//        AnhXa();
//        Intent intent = getIntent();
//        String city = intent.getStringExtra("name");
//
//        Log.d("Ket qua: ","Du Lieu Tuyen Qua: " + city);
//
//        if(city.equals("")){
//            tenTP = "HaNoi";
//            Get7DaysData(tenTP);
//        }
//        else{
//            tenTP = city;
//            Get7DaysData(tenTP);
//        }
//
//        imageBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//    }
//
//    private void AnhXa() {
//        imageBack = (ImageView) findViewById(R.id.imgBack);
//        txtName = (TextView) findViewById(R.id.txtCity);
//        lv = (ListView) findViewById(R.id.listview);
//
//        arr_thoiTiet = new ArrayList<ThoiTiet>();
//        customAdapter = new CustomAdapter(Next_SevenDay.this, arr_thoiTiet);
//
//        lv.setAdapter(customAdapter);
//
//    }
//
//    private void Get7DaysData(String data){
//       // String url = "https://api.openweathermap.org/data/2.5/forecast/daily?q="+ data +"&cnt=7&appid=cf0756b4f7be57376f7190fcb5231f17";
//
//        String url = "https://api.weatherapi.com/v1/forecast.json?key=2ee7dce485274901bbd152052240106&q="+ data+ "&days=1";
//
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue((Next_SevenDay.this));
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                      //  Log.i("Ket qua","Json: "+ response );
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//
//                            JSONObject jsonObject_city = jsonObject.getJSONObject("location");
//                            String name = jsonObject_city.getString("name");
//                            txtName.setText(name);
//                            //
//                            JSONObject jsonObject_forecast = jsonObject.getJSONObject("forecast");
//                            JSONArray jsonArrayList = jsonObject_forecast.getJSONArray("forecastday");
//
//                            // trong jsonArrayList có 7 cặp thẻ (tương ứng thoiTiet 7 ngày) nên dùng for để duyệt qua 7 cặp thẻ
//                            for (int i = 0 ; i< jsonArrayList.length();i++){
//                                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
//                                String ngay = jsonObjectList.getString("date_epoch");
//
//                                // chuyển từ dạng thời gian appid sang ngày giờ phút giây
//                                long l = Long.valueOf(ngay);
//                                Date date = new Date(l * 1000L);
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd  "+ "||"+ "  HH"+":"+"mm");
//                                String Day = simpleDateFormat.format(date);
//
//                                JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("day");
//                                String max = jsonObjectTemp.getString("maxtemp_c");
//                                String min =  jsonObjectTemp.getString("mintemp_c");
//
//                                Double a = Double.valueOf(max);// - 273; // kiểu double
//                                Double b = Double.valueOf(min);// - 273;
//                                String NhietDoMax = String.valueOf(a.intValue()); // chuyển về kiểu int
//                                String NhietDoMin = String.valueOf(b.intValue()); // chuyển về kiểu int
//
//                                ///
//
//                                JSONArray jsonArrayWeather = jsonObject.getJSONArray("condition");
//                                JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0); // vì trong weather chỉ có 1 đối tượng nên index = 0 luôn
//                                String status = jsonObjectWeather.getString("text");
//                                String icon = jsonObjectWeather.getString("icon");
//
//
//                                arr_thoiTiet.add(new ThoiTiet(Day, status, icon, NhietDoMax,NhietDoMin));
//                            }
//                            //nếu có dl mới thì sẽ được cập nhật
//                            customAdapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i("Loi","Json: "+ error );
//                    }
//                });
//        requestQueue.add(stringRequest);
//    }
//}
package com.example.app_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Next_SevenDay extends AppCompatActivity {

    String tenTP = "";
    ImageView imageBack;
    TextView txtName;
    ListView lv;

    CustomAdapter customAdapter;
    ArrayList<ThoiTiet> arr_thoiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_seven_day);

        AnhXa();
        Intent intent = getIntent();
        String city = intent.getStringExtra("name");

        Log.d("Ket qua: ","Du Lieu Tuyen Qua: " + city);

        if(city == null || city.equals("")){
            tenTP = "Hanoi";
        } else {
            tenTP = city;
        }
        Get7DaysData(tenTP);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void AnhXa() {
        imageBack = findViewById(R.id.imgBack);
        txtName = findViewById(R.id.txtCity);
        lv = findViewById(R.id.listview);

        arr_thoiTiet = new ArrayList<>();
        customAdapter = new CustomAdapter(Next_SevenDay.this, arr_thoiTiet);

        lv.setAdapter(customAdapter);
    }

    private void Get7DaysData(String data){
        String url = "https://api.weatherapi.com/v1/forecast.json?key=2ee7dce485274901bbd152052240106&q=" + data + "&days=7";

        RequestQueue requestQueue = Volley.newRequestQueue(Next_SevenDay.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject_city = jsonObject.getJSONObject("location");
                            String name = jsonObject_city.getString("name");
                            txtName.setText(name);

                            JSONObject jsonObject_forecast = jsonObject.getJSONObject("forecast");
                            JSONArray jsonArrayList = jsonObject_forecast.getJSONArray("forecastday");

                            for (int i = 0 ; i < jsonArrayList.length(); i++) {
                                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
                                String ngay = jsonObjectList.getString("date_epoch");

                                long l = Long.valueOf(ngay);
                                Date date = new Date(l * 1000L);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE"+"\n"+" yyyy-MM-dd  " );
                                String Day = simpleDateFormat.format(date);

                                JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("day");
                                String max = jsonObjectTemp.getString("maxtemp_c");
                                String min = jsonObjectTemp.getString("mintemp_c");

                                Double a = Double.valueOf(max);
                                Double b = Double.valueOf(min);
                                String NhietDoMax = String.valueOf(a.intValue());
                                String NhietDoMin = String.valueOf(b.intValue());

                                JSONObject jsonObjectCondition = jsonObjectTemp.getJSONObject("condition");
                                String status = jsonObjectCondition.getString("text");
                                String icon = jsonObjectCondition.getString("icon");

                                arr_thoiTiet.add(new ThoiTiet(Day, status, icon, NhietDoMax, NhietDoMin));
                            }

                            customAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Loi","Json: " + error );
                    }
                });
        requestQueue.add(stringRequest);
    }
}
