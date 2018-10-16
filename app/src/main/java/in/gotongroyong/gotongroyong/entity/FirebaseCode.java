package in.gotongroyong.gotongroyong.entity;

public class FirebaseCode {

    public static final int AUTH_EMAIL_LOGIN = 10;
    public static final int AUTH_FACEBOOK_LOGIN = 20;
    public static final int AUTH_GOOGLE_LOGIN = 30;

    public static final int AUTH_EMAIL_REGISTER = 50;

    public static final int AUTH_UNKNOWN_ERROR = 100;
    public static final int AUTH_WRONG_PASS = 101;
    public static final int AUTH_NOT_REGISTERED = 102;
    public static final int AUTH_SUCCESS = 200;

    public static boolean isSuccess(int code) {
        return code == 200;
    }
}
