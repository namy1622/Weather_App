package com.example.app_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    Button btnSearch, btnChangeActivity;
    TextView txtName, txtCountry, txtTemp, txtStatus, txtHumidity, txtCloud,txtWind, txtDay;
    ImageView imgIcon;

    String City = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        GetCurrentWeatherData("Hanoi");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edtSearch.getText().toString();

                if(city.equals("")){
                    City = "Hanoi";
                    GetCurrentWeatherData(City);
                }
                else{
                    City = city;
                    GetCurrentWeatherData(City);
                }

            }
        });

        //
        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city =  edtSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this,Next_SevenDay.class );

                intent.putExtra("name", city);
                startActivity(intent);
            }
        });
    }

    public void GetCurrentWeatherData(String data){
        RequestQueue requestQuere = Volley.newRequestQueue(MainActivity.this); // truyền vào màn hình đang đứng (context)
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ data + "&appid=cf0756b4f7be57376f7190fcb5231f17";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //  lấy dữ liệu từ JSON
                            // JSON ví dụ : https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=cf0756b4f7be57376f7190fcb5231f17

                            // lấy thời gian
                            String day;
                            day = jsonObject.getString("dt");  // gía trị mặc định lấy từ json là dạng thời gian appid
                            // lấy tên thành phố
                            String name = jsonObject.getString("name");
                            txtName.setText("Tên thành phố: " + name);

                            // chuyển từ dạng thời gian appid sang ngày giờ phút giây
                            long l = Long.valueOf(day);
                            Date date = new Date(l * 1000L);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy  "+ "||"+ "  HH"+":"+"mm");
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd " + "---" +  " HH");
//                            SimpleDateFormat simpleDateFormat_time = new SimpleDateFormat("mm");
                            String Day = simpleDateFormat.format(date);  // + ":" + simpleDateFormat_time.format(date);

                            txtDay.setText(Day);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather"); // weathẻ còn có mảng con bên trong nên gọi như này
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0); // vì weather chỉ có 1 phần tử nên gọi index = 0 luôn

                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                          //Picasso.with(MainActivity.this).load("https://openweathermap.org/img/wn/" + icon + ".png").into(imgIcon);
                            Picasso.get().load("https://openweathermap.org/img/wn/" + icon + ".png").into(imgIcon);
                            txtStatus.setText(status);

                            //
                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjectMain.getString("temp");
                            String doam = jsonObjectMain.getString("humidity");

                            Double a = Double.valueOf(nhietdo) - 273;
                          //  Float a = Float.valueOf(nhietdo) -273;
                            String NhietDo = String.valueOf(a.intValue());

                            txtTemp.setText(NhietDo+ " °C");
                            txtHumidity.setText(doam + "%");

                            //
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            txtWind.setText(gio + "m/s");

                            JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectClouds.getString("all");
                            txtCloud.setText(may + "%");

                            //
                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            txtCountry.setText("Tên quốc gia: " + country);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        // Xử lý phản hồi từ API
                        Log.i("KetQua", response);
                    }
                },
                new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Xử lý lỗi
                            Log.e("VolleyError", error.toString());
                        }
                });

        requestQuere.add(stringRequest);
    }
    private void AnhXa(){
        edtSearch = (EditText) findViewById(R.id.edittextSearch);

        btnSearch = (Button) findViewById(R.id.buttonSearch);
        btnChangeActivity = (Button) findViewById(R.id.buttonchangeActivity);

        imgIcon = (ImageView) findViewById(R.id.imageIcon);

        txtName = (TextView) findViewById(R.id.textviewCity);
        txtCountry = (TextView) findViewById(R.id.textviewCountry);
        txtTemp = (TextView) findViewById(R.id.textviewTemp);
        txtStatus = (TextView) findViewById(R.id.status);
        txtHumidity = (TextView) findViewById(R.id.textviewHumidity);
        txtCloud = (TextView) findViewById(R.id.textviewCloud);
        txtDay = (TextView) findViewById(R.id.textviewDay);
        txtWind = (TextView) findViewById(R.id.textviewWind);

    }
}