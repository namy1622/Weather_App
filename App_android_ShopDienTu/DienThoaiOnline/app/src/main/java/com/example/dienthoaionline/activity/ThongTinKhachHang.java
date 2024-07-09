package com.example.dienthoaionline.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dienthoaionline.R;
import com.example.dienthoaionline.ultil.checkConnectInternet;
import com.example.dienthoaionline.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {

    EditText edt_tenKhachHang , edt_email, edt_sdt;
    Button btn_xacNhan, btn_Exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);

        AnhXa();

        btn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }
        else{
            checkConnectInternet.showToast_Short(getApplicationContext(), "Kiểm tra kết nối Internet...");
        }
    }

    private void EventButton() {
        btn_xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_tenKhachHang.getText().toString().trim();
                String sdt = edt_sdt.getText().toString().trim();
                String email = edt_email.getText().toString().trim();

                if(ten.length() > 0 && sdt.length() > 0 && email.length() > 0 ){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server.url_ThongTinKhachHang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String madonhang) {
                                Log.d("arr don hang", madonhang);


                                if(Integer.parseInt(madonhang) > 0 ){
                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest  request = new StringRequest(Request.Method.POST, server.url_Chitietdonhang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                                if(response.equals("1")){
                                                    MainActivity.arr_giohang.clear();
                                                    checkConnectInternet.showToast_Short(getApplicationContext(), "Ban da them gio hang thanh cong");

                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    startActivity(intent);
                                                    checkConnectInternet.showToast_Short(getApplicationContext(), "Moi ban tiep tuc mua hang");

                                                }
                                                else{
                                                    checkConnectInternet.showToast_Short(getApplicationContext(), "Them gio hang that bai...");

                                                }
                                        }
                                    },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }){
                                        @Nullable
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
                                            for(int i = 0; i < MainActivity.arr_giohang.size(); i++){
                                                JSONObject jsonObject = new JSONObject();
                                                try {
                                                    jsonObject.put("madonhang", madonhang);
                                                    jsonObject.put("masanpham", MainActivity.arr_giohang.get(i).getId_sp());
                                                    jsonObject.put("tensanpham", MainActivity.arr_giohang.get(i).getTen_sp());
                                                    jsonObject.put("giasanpham", MainActivity.arr_giohang.get(i).getGia());
                                                    jsonObject.put("soluongsanpham", MainActivity.arr_giohang.get(i).getSoluong_sp());

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                   // throw new RuntimeException(e);
                                                }
                                                jsonArray.put(jsonObject);
                                            }

                                            HashMap<String, String> hashMap = new HashMap<String, String>();
                                            hashMap.put("json", jsonArray.toString());
                                            return hashMap;
                                        }
                                    };

                                    queue.add(request);
                                }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                                Log.e("LoiXacNhan", "----------");

                        }
                    })
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("sodienthoai", sdt);
                            hashMap.put("email", email);

                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else{
                    checkConnectInternet.showToast_Short(getApplicationContext(), "Kiểm tra lại dữ liệu đã nhập..." );
                }
            }
        });
    }

    private void AnhXa() {
        edt_tenKhachHang = (EditText) findViewById(R.id.edittext_tenKhachHang);
        edt_sdt = (EditText) findViewById(R.id.edittext_sdt_khachHang);
        edt_email = (EditText) findViewById(R.id.edittext_email);
        btn_xacNhan = (Button) findViewById(R.id.button_xacNhan);
        btn_Exit = (Button) findViewById(R.id.button_exit);
    }
}