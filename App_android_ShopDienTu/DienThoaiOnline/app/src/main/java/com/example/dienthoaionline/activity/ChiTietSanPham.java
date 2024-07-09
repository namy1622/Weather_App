package com.example.dienthoaionline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dienthoaionline.R;
import com.example.dienthoaionline.model.GioHang;
import com.example.dienthoaionline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {

    Toolbar toolbar_chitiet;
    ImageView img_chiTiet;
    TextView txt_ten, txt_gia, txt_mota;
    Spinner spinner;
    Button btn_datMua;
    //---------------
    int id = 0;
    String TenChiTiet = "";
    int Gia_chitiet = 0;
    String hinhanh_chitiet = "";
    String mota_chitiet = "";
    int Id_sanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        
        AnhXa();

        ActionToolBar();

        GetInfomation();
        
        CatchEvent_Spinner();
        
        Event_Button();
    }

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
    private void Event_Button() {
        btn_datMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arr_giohang.size() > 0 ){
                    int sl =  Integer.parseInt(spinner.getSelectedItem().toString());

                    boolean exists = false;
                    for(int i = 0; i < MainActivity.arr_giohang.size() ; i++){
                        if(MainActivity.arr_giohang.get(i).getId_sp() == id ){ // nếu có sẵn sp thì cộng thêm số lượng vào sanr phẩm đó
                            MainActivity.arr_giohang.get(i).setSoluong_sp(MainActivity.arr_giohang.get(i).getSoluong_sp() + sl);
                            if(MainActivity.arr_giohang.get(i).getSoluong_sp() >= 10){
                                MainActivity.arr_giohang.get(i).setSoluong_sp(10);
                            }
                            MainActivity.arr_giohang.get(i).setGia(Gia_chitiet * MainActivity.arr_giohang.get(i).getSoluong_sp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soLuong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long total_only_sp =  soLuong * Gia_chitiet;
                        MainActivity.arr_giohang.add(new GioHang(id,TenChiTiet, total_only_sp, hinhanh_chitiet, soLuong));
                    }
                }
                else{  // nếu ko có datta -> add data mới
                    int soLuong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long total_only_sp =  soLuong * Gia_chitiet;
                    MainActivity.arr_giohang.add(new GioHang(id,TenChiTiet, total_only_sp, hinhanh_chitiet, soLuong));
                }

                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEvent_Spinner() {
        Integer[] soLuong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, soLuong );
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInfomation() {

        //----------------------------
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongTin_sanPham");
            id = sanpham.getID();
            TenChiTiet = sanpham.getTensanpham();
            Gia_chitiet = sanpham.getGiasanpham();
            hinhanh_chitiet = sanpham.getHinhanhsanpham();
            mota_chitiet = sanpham.getMotasanpham();
            Id_sanpham = sanpham.getIDSanpham();

            txt_ten.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_gia.setText("Giá: " + decimalFormat.format(Gia_chitiet) + "đ");
        txt_mota.setText(mota_chitiet);
        Picasso.get().load(hinhanh_chitiet)
                .placeholder(R.drawable.null64)
                .error(R.drawable.error64)
                .into(img_chiTiet);

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar_chitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_chitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar_chitiet = (Toolbar) findViewById(R.id.toolbar_chiTietSanPham);
        img_chiTiet = (ImageView) findViewById(R.id.image_chitietSP);
        txt_ten = (TextView) findViewById(R.id.textview_ten_chitietsp);
        txt_gia = (TextView) findViewById(R.id.textview_gia_sp);
        txt_mota = (TextView) findViewById(R.id.textview_mota_chitietSP);
        spinner = (Spinner) findViewById(R.id.spinner);
        btn_datMua = (Button) findViewById(R.id.button_datmua);
    }
}