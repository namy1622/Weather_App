package com.example.dienthoaionline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
 import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dienthoaionline.R;
import com.example.dienthoaionline.adapter.Loaisp_Adapter;
import com.example.dienthoaionline.adapter.Sanpham_Adapter;
import com.example.dienthoaionline.model.GioHang;
import com.example.dienthoaionline.model.Loaisp;
import com.example.dienthoaionline.model.Sanpham;
import com.example.dienthoaionline.ultil.checkConnectInternet;
import com.example.dienthoaionline.ultil.server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

 //--- Khai baos biến --------------------------------------

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManhinhchinh;
    NavigationView navigationView;
    ListView listViewManhinhchinh;
    DrawerLayout drawerLayout;
    //-----------------------------------------------
    ArrayList<Loaisp> arrLoaisp;
    Loaisp_Adapter loaispAdapter;
   //---------------------------------------------
    ArrayList<Sanpham> arr_sanpham;
    Sanpham_Adapter sanphamAdapter;
    int id = 0;
    String tenLoaisp = "";
    String hinhanhLoaisp = "";
    //------------
    public static ArrayList<GioHang> arr_giohang;
//-----------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );

        AnhXa();

        // Kiểm tra có kết nối interner chưa
        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){   //getApplicationContext laf lấy màn hình hiện tại
            Toast.makeText(getApplication(), "co mang", Toast.LENGTH_LONG).show();
            ActionBar(); // thiết lập phần menu xổ ra

            ActionViewFlipper(); // phần quảng cáo chạy ngang

        //    TestJson();
            Log.e("TRUOC GETDL_LOAI_SP", "---------");
            GetDuLieuLoaisp(); // đổ dữ liệu loại sản sẩn vào phần listview bên trái màn hình
            Log.e("SAU_  GETDL_LOAI_SP", "---------");
           // GetDuLieuSPMoiNhat(); // dữ liệu mới nhất được đổ vào màn hình chính
            Log.e("SAU GETDL_SP_ MOI_NHAT", "---------");
            CatchOnItemListView();  // hàm gọi khi bấm vào ITEM trên lisview phần loại sanpham

        }
        else{
            Toast.makeText(getApplication(), "ko mang", Toast.LENGTH_LONG).show();

            checkConnectInternet.showToast_Short(getApplicationContext(), "Chưa kết nối Internet. Kết nối Internet trước!");
            finish(); // đongs luôn chương trình (bị văng)
        }
    }

//    private void TestJson() {
//        // URL đầu tiên
//        String url1 = "http://namhus226.top/android/get_loaisanpham.php?i=1";
//
//        // Tạo HttpClient với CookieStore
//        CookieStore cookieStore = (CookieStore) new BasicCookieStore();
//        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
//        HttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(globalConfig)
//                .setDefaultCookieStore((org.apache.http.client.CookieStore) cookieStore)
//                .build();
//
//        // Gửi yêu cầu GET đến URL đầu tiên
//        HttpGet request1 = new HttpGet(url1);
//        HttpResponse response1 = httpClient.execute(request1);
//        String htmlContent = EntityUtils.toString(response1.getEntity());
//
//        // Trích xuất mã JavaScript từ HTML
//        String scriptStart = "<script>";
//        String scriptEnd = "</script>";
//        int scriptStartIndex = htmlContent.indexOf(scriptStart) + scriptStart.length();
//        int scriptEndIndex = htmlContent.indexOf(scriptEnd);
//        String jsCode = htmlContent.substring(scriptStartIndex, scriptEndIndex);
//
//        // Chạy mã JavaScript để tạo giá trị cookie
//        try (Context context = Context.create("js")) {
//            context.eval("js", jsCode);
//            Value cookieValue = context.eval("js", "toHex(slowAES.decrypt(c,2,a,b))");
//            String cookie = cookieValue.asString();
//
//            // Thiết lập cookie
//            org.apache.http.cookie.Cookie httpCookie = new org.apache.http.impl.cookie.BasicClientCookie("__test", cookie);
//            cookieStore.addCookie(httpCookie);
//        }
//
//        // URL đích
//        String url2 = "http://namhus226.top/android/get_loaisanpham.php?i=2";
//
//        // Gửi yêu cầu GET đến URL đích với cookie đã thiết lập
//        HttpGet request2 = new HttpGet(url2);
//        HttpResponse response2 = httpClient.execute(request2);
//    }

    // gắn giỏ hàng vào toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // bắt sk khi ấn vào nút giỏ hàng
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_gioHang){ //  NẾU BẤM VÀO GIỎ HÀNG -- > CHUYỂN QUA MÀN HÌNH GIỎ HÀNG
            Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {

        //  setOnItemClick chứ ko phải set OnClick
        // khi bấm vào 1 ITEM trên listview
        listViewManhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            checkConnectInternet.showToast_Short(getApplicationContext(), "Vui lòng kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);  // đóng thanh menu
                        break;

                    case 1:
                        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);

                            intent.putExtra("id_loaisp",  arrLoaisp.get(position).getId()); //  putExtra: pthuc truyen dl vào màn hình khác

                            startActivity(intent);
                        }
                        else{
                            checkConnectInternet.showToast_Short(getApplicationContext(), "Vui lòng kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);  // đóng thanh menu
                        break;

                    case 2:
                        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);

                            intent.putExtra("id_loaisp",  arrLoaisp.get(position).getId()); //  putExtra: pthuc truyen dl vào màn hình khác

                            startActivity(intent);
                        }
                        else{
                            checkConnectInternet.showToast_Short(getApplicationContext(), "Vui lòng kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);  // đóng thanh menu
                        break;

                    case 3:
                        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            intent.putExtra("id_loaisp",  arrLoaisp.get(position).getId()); //  putExtra: pthuc truyen dl vào màn hình khác

                            startActivity(intent);
                        }
                        else{
                            checkConnectInternet.showToast_Short(getApplicationContext(), "Vui lòng kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);  // đóng thanh menu
                        break;

                    case 4:
                        if(checkConnectInternet.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);

                            startActivity(intent);
                        }
                        else{
                            checkConnectInternet.showToast_Short(getApplicationContext(), "Vui lòng kết nối Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);  // đóng thanh menu
                        break;

                }
            }
        });
    }

    // Volley: đọc dl từ URL;

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Log.e("SP_MOI_NHAT", "172, JSONARRAY URL");
        Log.e("SP_MOI_NHAT 173", server.url_sanphammoinhat);
      //  String url = "http://namhus226.top/android/get_loaisanpham.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.url_sanphammoinhat, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {  // reponse: là jsonArray gán vào
                // response : tồn tại, có gtri trả về
                Log.e("SP_MOI_NHAT", "ON_RESPONSE");
                if(response != null){
                    //-----------------------
                    int ID = 0;
                    String Tensanpham = "";
                    Integer Giasanpham = 0;
                    String Hinhanhsanpham = "";
                    String Motasanpham ="";
                    int IDsanpham = 0;
                    //-------------------------
                    for(int i = 0; i < response.length(); i++ ){
                        try {

                            Log.e("SP_MOI_NHAT", "TRY");
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            IDsanpham = jsonObject.getInt("idsanpham");
                            //------------
                            Log.e("ID", String.valueOf(ID));
                            Log.e("Ten", Tensanpham);
                            Log.e("Gia", String.valueOf(Giasanpham));
                            Log.e("Mota", Motasanpham);
                            //
                            arr_sanpham.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));

                            sanphamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    Log.e("Loi SanPhamMoi 1", responseBody);
                } else {
                    Log.e("Loi SanPhamMoi 2", "Network error: " + error.toString());
                }
            }
        });

        requestQueue.add(jsonArrayRequest);
    }



//    private void GetDuLieuLoaisp() {
//        // hàm này giup thực hiện các pthuc gửi lên server
//     //   Toast.makeText(getApplication(), "truoc volley", Toast.LENGTH_SHORT).show();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//     //   Toast.makeText(getApplication(), "sau volley", Toast.LENGTH_SHORT).show();
//
//        Log.e("truoc json","------");
//        Log.e("Loai SP 231", server.DuongdanLoaisp);
//
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongdanLoaisp, new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                Log.e("LOAI_SP 236", response.toString());
//                // nếu dk có thì bắt dl về
//                // nếu không thì ko bắt dl về tránh lỗi app
//                if(response != null){
//                    for(int i = 0; i < response.length(); i++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            id = jsonObject.getInt("id");
//                            Log.e("sau json",  jsonObject.getString("tenloaisp"));
//
//                            tenLoaisp = jsonObject.getString("tenloaisp");
//                            hinhanhLoaisp = jsonObject.getString("hinhanhloaisp");
//
//                            arrLoaisp.add(new Loaisp(id, tenLoaisp, hinhanhLoaisp));
//
//                            loaispAdapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    arrLoaisp.add(arrLoaisp.size(), new Loaisp(0, "Liên hệ", "https://cdn-icons-png.flaticon.com/128/6596/6596115.png" ));
//                    arrLoaisp.add(arrLoaisp.size(), new Loaisp(0, "Thông tin", "https://cdn-icons-png.flaticon.com/128/9485/9485489.png"));
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                checkConnectInternet.showToast_Short(getApplicationContext(),"*"+ error.toString() );
//                if (error.networkResponse != null) {
//                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                    Log.e("Loi LOAI_SAN_PHAMi 1", responseBody);
//                } else {
//                    Log.e("Loi LOAI_SAN_PHAMi 2", "Network error: " + error.toString());
//                }
//            }
//        });
//
//  //      Toast.makeText(getApplication(), "336+", Toast.LENGTH_SHORT).show();
//
//        requestQueue.add(jsonArrayRequest);
//
//    //    Toast.makeText(getApplication(), "370+", Toast.LENGTH_SHORT).show();
//
//    }
private void GetDuLieuLoaisp() {
    // Toast.makeText(getApplication(), "truoc volley", Toast.LENGTH_SHORT).show();

    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    // Toast.makeText(getApplication(), "sau volley", Toast.LENGTH_SHORT).show();

    Log.e("truoc json","------");
    Log.e("Loai SP 231", server.DuongdanLoaisp);

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongdanLoaisp, new Response.Listener<JSONArray>() {

        @Override
        public void onResponse(JSONArray response) {
            Log.e("LOAI_SP 236", response.toString());
            // nếu dk có thì bắt dl về
            // nếu không thì ko bắt dl về tránh lỗi app
            if (response != null) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String tenLoaisp = jsonObject.getString("tenloaisp");
                        String hinhanhLoaisp = jsonObject.getString("hinhanhloaisp");

                        arrLoaisp.add(new Loaisp(id, tenLoaisp, hinhanhLoaisp));

                        // Đảm bảo rằng bạn gọi notifyDataSetChanged() trên UI thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loaispAdapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        Log.e("JSON Error", e.toString());
                    }
                }
                arrLoaisp.add(new Loaisp(0, "Liên hệ", "https://cdn-icons-png.flaticon.com/128/6596/6596115.png"));
                arrLoaisp.add(new Loaisp(0, "Thông tin", "https://cdn-icons-png.flaticon.com/128/9485/9485489.png"));
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            checkConnectInternet.showToast_Short(getApplicationContext(), "*" + error.toString());
            if (error.networkResponse != null) {
                String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                Log.e("Loi LOAI_SAN_PHAMi 1", responseBody);
            } else {
                Log.e("Loi LOAI_SAN_PHAMi 2", "Network error: " + error.toString());
            }
        }
    });

    requestQueue.add(jsonArrayRequest);
}

    private void ActionViewFlipper() {
        ArrayList<String> arrQuangCao = new ArrayList<>(); // lưu đường dẫn ảnh;
            arrQuangCao.add("https://th.bing.com/th?id=OIF.gXH%2b0sPvHbgDWbe44TNxoA&rs=1&pid=ImgDetMain");
            arrQuangCao.add("https://th.bing.com/th/id/R.cfb414295da7d8ebd8bb7b291373ff9f?rik=hgGEoZqVlddEQQ&riu=http%3a%2f%2fgioneemobile.vn%2fwp-content%2fuploads%2f2019%2f10%2fcac-dong-dien-thoai-samsung-moi-nhat-hien-nay.jpg&ehk=anAy8PWrNRwg33rYYxKjqu7LmStbhSHgErYiuIKwVhg%3d&risl=&pid=ImgRaw&r=0");
            arrQuangCao.add("https://cdn.tgdd.vn/Files/2022/10/13/1478637/surface-laptop-5_1280x720-800-resize.jpg");
           arrQuangCao.add("https://o.aolcdn.com/images/dims?quality=85&image_uri=https:%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fcrop%3D1800%252C1100%252C0%252C0%26quality%3D85%26format%3Djpg%26resize%3D1600%252C978%26image_uri%3Dhttps%253A%252F%252Fs.yimg.com%252Fos%252Fcreatr-uploaded-images%252F2019-07%252F379d58d0-aa5d-11e9-beff-7a3b25ef2046%26client%3Da1acac3e1b3290917d92%26signature%3D47abd0dfc739c90271f47358f8f52a55f9265602&client=amp-blogside-v2&signature=69987cd3a527c27862efdf14839b5490a0aba365");

           for (int i = 0; i<arrQuangCao.size(); i++){
               ImageView imageView = new ImageView(getApplicationContext());

               Picasso.get().load(arrQuangCao.get(i)).into(imageView); // đổ đường dẫn ảnh qua Picasso, rồi gán cho imageView
               imageView.setScaleType(ImageView.ScaleType.FIT_XY); // căn chỉnh ảnh cho vừa với viewFlipper

               viewFlipper.addView(imageView);

           }
           viewFlipper.setFlipInterval(5000); // set thời gian chạy của viewFlipper
            viewFlipper.setAutoStart(true); // set tự động chạy khi chương trình bắt đầu

        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // thiết lập để hiện nút menu
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size); // thiết lập ico n

        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // hàm để gọi menu ra
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START); // nhảy ra giữa
            }
        });
    }

    private void AnhXa() {
        toolbar =(Toolbar) findViewById(R.id.toolbarManhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerViewManhinhchinh = (RecyclerView) findViewById(R.id.recyclerviewSanpham);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        listViewManhinhchinh = (ListView) findViewById(R.id.listviewManHinhChinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        //--------------------
        arrLoaisp = new ArrayList<>();
        arrLoaisp.add(0, new Loaisp(0, "Trang chủ", "https://cdn-icons-png.flaticon.com/128/9073/9073032.png"));

       // arrLoaisp.add(0, new Loaisp(0, "test", R.drawable.logololi));
        loaispAdapter = new Loaisp_Adapter(arrLoaisp, getApplicationContext());
        //----------------------
        listViewManhinhchinh.setAdapter(loaispAdapter);
        //----------------------
        arr_sanpham = new ArrayList<>();
        sanphamAdapter = new Sanpham_Adapter(getApplicationContext(), arr_sanpham);

        recyclerViewManhinhchinh.setHasFixedSize(true); //
        recyclerViewManhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));  // set 2 cột
        recyclerViewManhinhchinh.setAdapter(sanphamAdapter);
        //----------------------

        // khi ! null ( đã có data) -> ko cấp phát bộ nhớ mới lamf mất datta
        if(arr_giohang != null){

        }
        else{ // nếu null thì cấp phát mới
            arr_giohang = new ArrayList<>();
        }
    }
}