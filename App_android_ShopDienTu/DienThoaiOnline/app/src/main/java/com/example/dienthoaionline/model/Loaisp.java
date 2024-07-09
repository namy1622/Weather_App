package com.example.dienthoaionline.model;

public class Loaisp {
    public int Id;
    public String Tenloaisp;
    public String Hinhanhloaisp;

    public int HinhanhLoaisp_Draw;

    public int getHinhanhLoaisp_Draw() {
        return HinhanhLoaisp_Draw;
    }

    public void setHinhanhLoaisp_Draw(int hinhanhLoaisp_Draw) {
        HinhanhLoaisp_Draw = hinhanhLoaisp_Draw;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }

    public Loaisp(int id, String tenloaisp, int hinhanhLoaisp_Draw) {
        Id = id;
        Tenloaisp = tenloaisp;
        HinhanhLoaisp_Draw = hinhanhLoaisp_Draw;
    }

    public Loaisp(int id, String tenloaisp, String hinhanhloaisp) {
        Id = id;
        Tenloaisp = tenloaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }
}
