package com.example.dienthoaionline.ultil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.net.NetworkInterface;

public class checkConnectInternet {
    // hamf kiểm tra xem có ketnoi mang không
    //static để sử dụng được trên nhiều màn hình
    public static boolean haveNetworkConnection(Context context){
         boolean haveConnected_Wifi = false;
         boolean haveConnected_Mobile = false;

        ConnectivityManager cm = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for(NetworkInfo ni : netInfo){
            if(ni.getTypeName().equalsIgnoreCase("WIFI")){
                if(ni.isConnected()){
                    haveConnected_Wifi = true;
                }
            }
            if(ni.getTypeName().equalsIgnoreCase("MOBILE")){
                if(ni.isConnected()){
                    haveConnected_Mobile = true;
                }
            }
        }

        return haveConnected_Wifi || haveConnected_Mobile ;
    }

    public static void showToast_Short(Context context, String thongbao){
        Toast.makeText(context, thongbao, Toast.LENGTH_LONG).show();
    }
}
