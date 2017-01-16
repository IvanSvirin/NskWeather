package com.example.ivansv.nskweather;

public class Util {

    public static String getDayTime(String in) {
        String[] out = {"ночь", "утро", "день", "вечер"};
        return out[Integer.parseInt(in)/6];
    }
}
