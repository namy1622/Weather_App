package com.example.dienthoaionline.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dienthoaionline.R;
import com.example.dienthoaionline.activity.ChiTietSanPham;
import com.example.dienthoaionline.model.Sanpham;
import com.example.dienthoaionline.ultil.checkConnectInternet;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sanpham_Adapter extends RecyclerView.Adapter<Sanpham_Adapter.ItemHolder> {

    Context context;
    ArrayList<Sanpham> arrsanpham;

    public Sanpham_Adapter(Context context, ArrayList<Sanpham> arrsanpham) {
        this.context = context;
        this.arrsanpham = arrsanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat, null);

        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;

//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_listview_loaisp, parent, false);
//        return new ItemHolder(v);
    }

    // func này  hỗ trợ get và set lên layout
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = arrsanpham.get(position);


        Log.e("check " + position , sanpham.getTensanpham());
        Log.e("check " + position, String.valueOf(sanpham.getGiasanpham()));
        Log.e("check " + position, sanpham.Hinhanhsanpham);


        //------------------------------------------------

        try {
            holder.txttensanpham.setText(sanpham.getTensanpham());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            holder.txtgiasanpham.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham()) + "đ");
            Picasso.get().load(sanpham.getHinhanhsanpham())
                    .placeholder(R.drawable.null64)
                    .error(R.drawable.error)
                    .into(holder.imghinhsanpham);
        } catch (NullPointerException e) {
            Log.e("Sanpham_Adapter", "Lỗi khi set dữ liệu: ", e);
        }
    }

    @Override
    public int getItemCount() {
        return arrsanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham, txtgiasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

//            imghinhsanpham = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
//            txtgiasanpham = (TextView) itemView.findViewById(R.id.textviewgiasanpham);
//            txttensanpham = (TextView) itemView.findViewById(R.id.textviewtensanpham);

            imghinhsanpham = itemView.findViewById(R.id.imageviewsanpham);
            txtgiasanpham = itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham = itemView.findViewById(R.id.textviewtensanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPham.class);
                    intent.putExtra("thongTin_sanPham", arrsanpham.get(getLayoutPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    checkConnectInternet.showToast_Short(context, arrsanpham.get(getLayoutPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });


        }
    }
}
