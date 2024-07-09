package com.example.dienthoaionline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienthoaionline.R;
import com.example.dienthoaionline.activity.GioHangActivity;
import com.example.dienthoaionline.activity.MainActivity;
import com.example.dienthoaionline.model.GioHang;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHang_Adapter extends BaseAdapter {
    Context context;
     ArrayList<GioHang> arr_gioHang;

    public GioHang_Adapter(Context context, ArrayList<GioHang> arr_gioHang) {
        this.context = context;
        this.arr_gioHang = arr_gioHang;
    }

    @Override
    public int getCount() {
        return arr_gioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_gioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txt_ten_giohang, txt_gia_giohang;
        public ImageView img_giohang;
        public Button btn_minus, btn_values, btn_plus;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       final  ViewHolder viewHolder;// = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_gio_hang, null);
            viewHolder.txt_ten_giohang = (TextView) convertView.findViewById(R.id.textview_ten_giohang);
            viewHolder.txt_gia_giohang = (TextView) convertView.findViewById(R.id.textview_gia_giohang);
            viewHolder.img_giohang = (ImageView) convertView.findViewById(R.id.image_gioHang);
            viewHolder.btn_minus = convertView.findViewById(R.id.button_minus);
            viewHolder.btn_values = convertView.findViewById(R.id.button_values);
            viewHolder.btn_plus = convertView.findViewById(R.id.button_plus);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();

        }

        GioHang gioHang = (GioHang) getItem(position);;
        viewHolder.txt_ten_giohang.setText(gioHang.getTen_sp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_gia_giohang.setText(decimalFormat.format(gioHang.getGia()) + "đ");

        Picasso.get().load(gioHang.getHinh_sp())
                .placeholder(R.drawable.null64)
                .error(R.drawable.error64)
                .into(viewHolder.img_giohang);

        viewHolder.btn_values.setText(gioHang.getSoluong_sp() + "");

        int sl =Integer.parseInt(viewHolder.btn_values.getText().toString());
        if(sl >= 10){
            viewHolder.btn_plus.setVisibility(View.INVISIBLE);
            viewHolder.btn_minus.setVisibility(View.VISIBLE);
        }
        else if(sl <= 1){
            viewHolder.btn_minus.setVisibility(View.INVISIBLE);
        }
        else if(sl >= 1){
            viewHolder.btn_minus.setVisibility(View.VISIBLE);
            viewHolder.btn_plus.setVisibility(View.VISIBLE);
        }

        viewHolder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl_new = Integer.parseInt(viewHolder.btn_values.getText().toString()) + 1;
                int sl_hientai = MainActivity.arr_giohang.get(position).getSoluong_sp();
                long gia_hientai = MainActivity.arr_giohang.get(position).getGia();

                MainActivity.arr_giohang.get(position).setSoluong_sp(sl_new);
                long gia_new = (gia_hientai * sl_new)/  sl_hientai;
                MainActivity.arr_giohang.get(position).setGia(gia_new);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txt_gia_giohang.setText(decimalFormat.format(gia_new) + "đ");

                GioHangActivity.EventUltil(); // goij lại hàm để update data

                if(sl_new > 9 ){
                    viewHolder.btn_plus.setVisibility(View.INVISIBLE);
                    viewHolder.btn_minus.setVisibility(View.VISIBLE);
                    viewHolder.btn_values.setText(String.valueOf(sl_new));
                }
                else{
                    viewHolder.btn_plus.setVisibility(View.VISIBLE);
                    viewHolder.btn_minus.setVisibility(View.VISIBLE);
                    viewHolder.btn_values.setText(String.valueOf(sl_new));
                }
            }
        });

        viewHolder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl_new = Integer.parseInt(viewHolder.btn_values.getText().toString()) - 1;
                int sl_hientai = MainActivity.arr_giohang.get(position).getSoluong_sp();
                long gia_hientai = MainActivity.arr_giohang.get(position).getGia();

                MainActivity.arr_giohang.get(position).setSoluong_sp(sl_new);
                long gia_new = (gia_hientai * sl_new)/  sl_hientai;
                MainActivity.arr_giohang.get(position).setGia(gia_new);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txt_gia_giohang.setText(decimalFormat.format(gia_new) + "đ");

                GioHangActivity.EventUltil(); // goij lại hàm để update data

                if(sl_new < 2 ){
                    viewHolder.btn_plus.setVisibility(View.VISIBLE);
                    viewHolder.btn_minus.setVisibility(View.INVISIBLE);
                    viewHolder.btn_values.setText(String.valueOf(sl_new));
                }
                else{
                    viewHolder.btn_plus.setVisibility(View.VISIBLE);
                    viewHolder.btn_minus.setVisibility(View.VISIBLE);
                    viewHolder.btn_values.setText(String.valueOf(sl_new));
                }
            }
        });
        return convertView;
    }
}
