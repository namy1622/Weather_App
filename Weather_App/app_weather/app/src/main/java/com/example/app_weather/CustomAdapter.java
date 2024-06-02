package com.example.app_weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ThoiTiet> arrayList;

    public CustomAdapter(Context context, ArrayList<ThoiTiet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.weather_day,null);

        ThoiTiet thoiTiet = arrayList.get(position);

        TextView txtDay = (TextView) convertView.findViewById(R.id.tvDay);
        TextView txtStatus = (TextView) convertView.findViewById(R.id.tvTrangThai);
        TextView txtMaxTemp = (TextView) convertView.findViewById(R.id.tvMaxTemp);
        TextView txtMinTemp = (TextView) convertView.findViewById(R.id.tvMinTemp);
        ImageView imgStatus = (ImageView) convertView.findViewById(R.id.imageTrangThai);

        txtDay.setText(thoiTiet.Day);
        txtStatus.setText(thoiTiet.status);
        txtMaxTemp.setText(thoiTiet.MaxTemp + " °C");
        txtMinTemp.setText(thoiTiet.MinTemp + " °C");

        Picasso.get().load("https:" + thoiTiet.Image +"" ).into(imgStatus);
        //Picasso.get().load("https://openweathermap.org/img/wn/" + thoiTiet.Image + ".png").into(imgStatus);

        return convertView;
    }
}
