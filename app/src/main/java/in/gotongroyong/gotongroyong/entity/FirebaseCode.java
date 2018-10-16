package in.gotongroyong.gotongroyong.entity;

public class FirebaseCode {

    public static final int AUTH_LOGIN_FAILED = 100;
    public static final int AUTH_LOGIN_WRONG_PASS = 101;
    public static final int AUTH_LOGIN_SUCCESS = 200;

    public static boolean isLoginSuccess(int code) {
        return code == 200;
    }

    public static boolean isLoginFailed(int code) {
        return code >= 100 && code < 200;
    }
}
