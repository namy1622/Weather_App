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

public class DienThoai_Adapter extends BaseAdapter {
    Context context;  // biến này là màn hình để đổ dl lên
    ArrayList<Sanpham> array_Dienthoai;

    public DienThoai_Adapter(Context context, ArrayList<Sanpham> array_Dienthoai) {
        this.context = context;
        this.array_Dienthoai = array_Dienthoai;
    }

    @Override
    public int getCount() {
        return array_Dienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return array_Dienthoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // giúp  khi có dl thì sẽ gán lại  --> ko phải gán lại nhiều lần
    public class ViewHolder{
        public TextView txt_ten_Dienthoai, txt_gia_Dienthoai, txt_mota_Dienthoai;
        public ImageView img_Dienthoai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_dienthoai, null);
            viewHolder.txt_ten_Dienthoai = (TextView) convertView.findViewById(R.id.textview_tensanpham);
            viewHolder.txt_gia_Dienthoai = (TextView) convertView.findViewById(R.id.textview_gia);
            viewHolder.txt_mota_Dienthoai = (TextView) convertView.findViewById(R.id.textview_mota_dienthoai);
            viewHolder.img_Dienthoai = (ImageView) convertView.findViewById(R.id.imageview_dienthoai);

            // xét, truyênf vào cho convertview
            convertView.setTag(viewHolder);
        }
        else{ //khi đã có dl rồi gì chỉ cần gán anhxa
                viewHolder = (ViewHolder)  convertView.getTag();
        }
        //---------------
        Sanpham sanpham = (Sanpham) getItem(position);

        viewHolder.txt_ten_Dienthoai.setText(sanpham.getTensanpham());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_gia_Dienthoai.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham()) + "đ");

        viewHolder.txt_mota_Dienthoai.setMaxLines(2); // set hiển thị tối đa 2 dòng
        viewHolder.txt_mota_Dienthoai.setEllipsize(TextUtils.TruncateAt.END ); // ddịnh dạng nếu dài quá thì chuyển thành dấu ...
        viewHolder.txt_mota_Dienthoai.setText(sanpham.getMotasanpham());

        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.null64)
                .error(R.drawable.error64)
                .into(viewHolder.img_Dienthoai);
        return convertView;
    }
}
