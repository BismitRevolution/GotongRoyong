package in.gotongroyong.gotongroyong.common;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {

    public static String toDecimal(int number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
}
