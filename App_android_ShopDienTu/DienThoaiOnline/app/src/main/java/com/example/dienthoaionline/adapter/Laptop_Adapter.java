package com.example.dienthoaionline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienthoaionline.R;
import com.example.dienthoaionline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Laptop_Adapter extends BaseAdapter {
    Context context;  // biến này là màn hình để đổ dl lên
    ArrayList<Sanpham> array_Laptop;

    public Laptop_Adapter(Context context, ArrayList<Sanpham> array_Laptop) {
        this.context = context;
        this.array_Laptop = array_Laptop;
    }

    @Override
    public int getCount() {
        return array_Laptop.size();
    }

    @Override
    public Object getItem(int position) {
        return array_Laptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // giúp  khi có dl thì sẽ gán lại  --> ko phải gán lại nhiều lần
    public class ViewHolder{
        public TextView txt_ten_Laptop, txt_gia_Laptop, txt_mota_Laptop;
        public ImageView img_Laptopi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Laptop_Adapter.ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_laptop, null);
            viewHolder.txt_ten_Laptop = (TextView) convertView.findViewById(R.id.textview_ten_Laptop);
            viewHolder.txt_ten_Laptop = (TextView) convertView.findViewById(R.id.textview_gia_Laptop);
            viewHolder.txt_ten_Laptop = (TextView) convertView.findViewById(R.id.textview_mota_laptop);
            viewHolder.img_Laptopi = (ImageView) convertView.findViewById(R.id.imageview_laptopi);

            // xét, truyênf vào cho convertview
            convertView.setTag(viewHolder);
        }
        else{ //khi đã có dl rồi gì chỉ cần gán anhxa
            viewHolder = (Laptop_Adapter.ViewHolder)  convertView.getTag();
        }
        //---------------
        Sanpham sanpham = (Sanpham) getItem(position);

        viewHolder.txt_ten_Laptop.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_gia_Laptop.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham()) + "đ");

        viewHolder.txt_mota_Laptop.setMaxLines(2); // set hiển thị tối đa 2 dòng
        viewHolder.txt_mota_Laptop.setEllipsize(TextUtils.TruncateAt.END ); // ddịnh dạng nếu dài quá thì chuyển thành dấu ...
        viewHolder.txt_mota_Laptop.setText(sanpham.getMotasanpham());

        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.null64)
                .error(R.drawable.error64)
                .into(viewHolder.img_Laptopi);
        return convertView;
    }
}
