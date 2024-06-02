package com.example.app_weather;

public class ThoiTiet {
    public String Day;
    public String status;
    public String Image;
    public String MaxTemp;
    public String MinTemp;

    public ThoiTiet(String day, String status, String image, String maxTemp, String minTemp) {
        Day = day;
        this.status = status;
        Image = image;
        MaxTemp = maxTemp;
        MinTemp = minTemp;
    }
}
