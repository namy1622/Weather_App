package com.example.dienthoaionline.model;

public class GioHang {
    public int id_sp;
    public String ten_sp;
    public long gia;
    public String hinh_sp;
    public int soluong_sp;

    public GioHang(int id_sp, String ten_sp, long gia, String hinh_sp, int soluong_sp) {
        this.id_sp = id_sp;
        this.ten_sp = ten_sp;
        this.gia = gia;
        this.hinh_sp = hinh_sp;
        this.soluong_sp = soluong_sp;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getHinh_sp() {
        return hinh_sp;
    }

    public void setHinh_sp(String hinh_sp) {
        this.hinh_sp = hinh_sp;
    }

    public int getSoluong_sp() {
        return soluong_sp;
    }

    public void setSoluong_sp(int soluong_sp) {
        this.soluong_sp = soluong_sp;
    }
}
