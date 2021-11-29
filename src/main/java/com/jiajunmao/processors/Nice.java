package com.jiajunmao.processors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Nice {

    public static String niceString(String str_, int length) {
        StringBuilder sb = new StringBuilder();
        if (str_.length() > length) {
            sb.append(str_, 0, length);
            sb.append("...");
            return sb.toString();
        } else {
            return str_;
        }
    }

    public static String breakString(String str_, int length) {
        StringBuilder sb = new StringBuilder();

        int left = 0;
        int right = length;
        while (right < str_.length()) {
            while (right < str_.length() && str_.charAt(right) != ' ') right++;
            if (right == str_.length()) break;

            sb.append(str_, left, right);
            sb.append("\n");

            left = right + 1;
            right = left + length;
        }

        if (left < str_.length()) {
            sb.append(str_, left, str_.length());
        }

        return sb.toString();
    }

    public static String nicePriority(String prio_) {
        return prio_.substring(0, 1).toUpperCase(Locale.ROOT);
    }

    public static String niceDate(String date_) {
        return niceDate(date_, true);
    }

    public static String niceDate(String date_, boolean listAll) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat format = new SimpleDateFormat(listAll ? "MM-dd \n HH:mm" : "MM-dd HH:mm:ss");
        try {
            Date date = inputFormat.parse(date_);
            return format.format(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
