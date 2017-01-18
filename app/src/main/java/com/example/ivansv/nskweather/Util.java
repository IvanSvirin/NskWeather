package com.example.ivansv.nskweather;

public class Util {

    public static String getDayTime(String in) {
        String[] out = {"ночь", "утро", "день", "вечер"};
        return out[Integer.parseInt(in)];
    }

    public static String getAverage(String min, String max) {
        return String.valueOf((Integer.parseInt(min) + Integer.parseInt(max)) / 2);
    }

    public static String getPhenomena(String cloudiness, String precipitation, String spower) {
        String[] cloudinessArray = {"Ясно","Малооблачно","Облачно","Пасмурно"};
        String[] precipitationArray = {"","","","","дождь","ливень","снег","снег","гроза","","без осадков"};
        return cloudinessArray[Integer.parseInt(cloudiness)].concat(", ").concat(precipitationArray[Integer.parseInt(precipitation)]);
    }

    public static String getDirection(String in) {
        String[] out = {"С","СВ","В","ЮВ","Ю","ЮЗ","З","СЗ"};
        return out[Integer.parseInt(in)];
    }

    public static int getIcon(String tod, String cloudiness, String precipitation, String spower) {
        if (tod.equals("1") || tod.equals("2")) {
            switch (cloudiness) {
                case "0":
                    return R.drawable.clear_day;
                case "1":
                    return R.drawable.partly_cloudy;
                case "2":
                    switch (precipitation) {
                        case "4":
                        case "5":
                            return spower.equals("0") ? R.drawable.rainy_day : R.drawable.storm_weather_day;
                        case "6":
                        case "7":
                            return R.drawable.snow_day;
                        case "8":
                            return R.drawable.thunder_day;
                        case "10":
                            return R.drawable.mostly_cloudy;
                    }
                    break;
                case "3":
                    switch (precipitation) {
                        case "4":
                        case "5":
                            return spower.equals("0") ? R.drawable.rainy_weather : R.drawable.storm_weather;
                        case "6":
                        case "7":
                            return R.drawable.snow_weather;
                        case "8":
                            return R.drawable.thunder_weather;
                        case "10":
                            return R.drawable.cloudy_weather;
                    }
                    break;
            }
        } else {
            switch (cloudiness) {
                case "0":
                    return R.drawable.clear_night;
                case "1":
                    return R.drawable.partly_cloudy_night;
                case "2":
                    switch (precipitation) {
                        case "4":
                        case "5":
                            return spower.equals("0") ? R.drawable.rainy_night : R.drawable.storm_weather_night;
                        case "6":
                        case "7":
                            return R.drawable.snow_night;
                        case "8":
                            return R.drawable.thunder_night;
                        case "10":
                            return R.drawable.mostly_cloudy_night;
                    }
                    break;
                case "3":
                    switch (precipitation) {
                        case "4":
                        case "5":
                            return spower.equals("0") ? R.drawable.rainy_weather : R.drawable.storm_weather;
                        case "6":
                        case "7":
                            return R.drawable.snow_weather;
                        case "8":
                            return R.drawable.thunder_weather;
                        case "10":
                            return R.drawable.cloudy_weather;
                    }
                    break;
            }
        }
        return R.drawable.clear_day;
    }
}
