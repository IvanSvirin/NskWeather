package com.example.ivansv.nskweather;

public class Util {

    public static String getDayTime(String in) {
        String[] out = {"ночь", "утро", "день", "вечер"};
        return out[Integer.parseInt(in) / 6];
    }

    public static int getIcon36(String hour, String cloudiness, String precipitation) {
        if (Integer.parseInt(precipitation) > 10) {
            return R.drawable.ic_weather_snowy_36;
        } else {
            if (hour.equals("04") || hour.equals("22")) {
                if (Integer.parseInt(cloudiness) < 3) {
                    return R.drawable.ic_weather_night_36;
                } else {
                    return R.drawable.ic_weather_cloudy_36;
                }
            } else {
                if (Integer.parseInt(cloudiness) < 3) {
                    return R.drawable.ic_weather_sunny_36;
                } else if (Integer.parseInt(cloudiness) < 7) {
                    return R.drawable.ic_weather_partlycloudy_36;
                } else {
                    return R.drawable.ic_weather_cloudy_36;
                }
            }
        }
    }
}
