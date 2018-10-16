package in.gotongroyong.gotongroyong.common;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {

    public static String toDecimal(int number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    public static boolean isValidEmail(CharSequence text) {
        return (!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }
}
