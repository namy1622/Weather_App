package com.example.dienthoaionline.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.dienthoaionline.R;
import com.example.dienthoaionline.adapter.GioHang_Adapter;
import com.example.dienthoaionline.model.GioHang;
import com.example.dienthoaionline.ultil.checkConnectInternet;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    ListView lv_giohang;
    TextView txt_thongbao;
    static TextView txt_tongTien;
    Button btn_ThanhToan, btn_TiepTucMua;
    Toolbar toolbar_giohang;
    GioHang_Adapter giohang_adapter;

    //--------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        AnhXa();

        ActionToolbar();

        CheckData();

        EventUltil();
        
        CatchOnItemListView();  // khi nhấn giữ vào 1 món hàng

        Log.e(" truoc EventButton", "---------------");
        EventButton();
        Log.e(" sau EventButton", "---------------");
    }

    private void EventButton() {
        Log.e(" truoc btn_tieptucmua", "---------------");
        btn_TiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.arr_giohang.size() > 0 ){

                    Intent intent = new Intent(getApplicationContext(), ThongTinKhachHang.class);

                    startActivity(intent);

                }
                else{

                    checkConnectInternet.showToast_Short(getApplicationContext(), "Giỏ hàng của bạn trống...");
                }
            }
        });

    }



    private void CatchOnItemListView() {
        lv_giohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa khỏi giỏ hàng");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.arr_giohang.size() <= 0){
                            txt_thongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            MainActivity.arr_giohang.remove(position);
                            giohang_adapter.notifyDataSetChanged();
                            EventUltil();

                            if(MainActivity.arr_giohang.size() <= 0){
                                txt_thongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                txt_thongbao.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohang_adapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });

                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0;
        for(int i = 0; i< MainActivity.arr_giohang.size(); i++){
            tongtien += MainActivity.arr_giohang.get(i).getGia();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_tongTien.setText(decimalFormat.format(tongtien) + "đ");
    }

    private void CheckData() {
        if(MainActivity.arr_giohang.size() <= 0){ // khi giỏ hàng rỗng
            giohang_adapter.notifyDataSetChanged();
            txt_thongbao.setVisibility(View.VISIBLE); // hiện thông báo ko có hàng
            lv_giohang.setVisibility(View.INVISIBLE); // ẩn listview giỏ hàng đi
        }
        else{
            giohang_adapter.notifyDataSetChanged();
            txt_thongbao.setVisibility(View.INVISIBLE); // ẩn thông báo ko có hàng
            lv_giohang.setVisibility(View.VISIBLE); // hiện  listview giỏ hàng đi
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar_giohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_giohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        lv_giohang = (ListView) findViewById(R.id.listview_giohang);
        txt_thongbao =(TextView) findViewById(R.id.textview_thongbao_giohang);
        txt_tongTien = (TextView) findViewById(R.id.textview_tongtien);
        btn_ThanhToan = (Button) findViewById(R.id.btn_thanhtoan_giohang);
        btn_TiepTucMua = (Button) findViewById(R.id.btn_tieptuc_muahang);
        toolbar_giohang = (Toolbar) findViewById(R.id.toolbar_giohang);

        giohang_adapter = new GioHang_Adapter(GioHangActivity.this, MainActivity.arr_giohang);
        lv_giohang.setAdapter(giohang_adapter);
    }
}