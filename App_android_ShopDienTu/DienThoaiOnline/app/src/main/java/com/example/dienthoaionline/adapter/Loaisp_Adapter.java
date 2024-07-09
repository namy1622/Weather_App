package com.example.dienthoaionline.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dienthoaionline.R;
import com.example.dienthoaionline.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Loaisp_Adapter extends BaseAdapter {
    ArrayList<Loaisp> arraylistLoaisp;
    Context context;

    public Loaisp_Adapter(ArrayList<Loaisp> arraylistLoaisp, Context context) {
        this.arraylistLoaisp = arraylistLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistLoaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtTenloaisp;
        ImageView imgLoaisp;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        // giống như khi vừa tải chương trình về thì nó còn null
        if(convertView == null){
            viewHolder = new ViewHolder();

            // giups lấy layout ra
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp, null);

            viewHolder.txtTenloaisp = (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgLoaisp = (ImageView) convertView.findViewById(R.id.imageviewloaisp);

            convertView.setTag(viewHolder);  // gán giá trị của viewHolder

        }
        else{ // nếu như đã có dl sẵn rồi ( sau lần đầu chạy chương trình )
            viewHolder = (ViewHolder) convertView.getTag(); // thì sẽ gán luôn vào viewHolder mà ko cần chạy giống như vừa tại ctrinhf về

        }

        Loaisp loaisp = (Loaisp) getItem(position);
        viewHolder.txtTenloaisp.setText(loaisp.getTenloaisp());
        viewHolder.imgLoaisp.setImageResource(R.drawable.logololi);

        Picasso.get().load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.null64) // khi chưa load ảnh
                .error(R.drawable.error64)     // khi load ảnh lỗi
                .into(viewHolder.imgLoaisp);


        return convertView;

        }

    }

