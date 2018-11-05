package in.gotongroyong.gotongroyong.entity;

public class API {

    public static final String TEST_URL = "http://go-webservice.herokuapp.com";
    public static final String GOTONG_ROYONG_LOCAL_URL = "http://localhost:8000";
    public static final String GOTONG_ROYONG_DEV_URL = "http://gotongroyong.cymonevo.com";
    public static final String GOTONG_ROYONG_PROD_URL = "https://gotongroyong.online";

//    AUTHENTICATION
    public static final int AUTH_EMAIL_LOGIN = 110;
    public static final int AUTH_FACEBOOK_LOGIN = 120;
    public static final int AUTH_GOOGLE_LOGIN = 130;

    public static final int AUTH_EMAIL_REGISTER = 111;
    public static final int AUTH_FACEBOOK_REGISTER = 121;
    public static final int AUTH_GOOGLE_REGISTER = 131;

    public static final int FIREBASE_EMAIL_LOGIN = 140;
    public static final int FIREBASE_FACEBOOK_LOGIN = 150;
    public static final int FIREBASE_GOOGLE_LOGIN = 160;

    public static final int FIREBASE_EMAIL_REGISTER = 141;
    public static final int FIREBASE_FACEBOOK_REGISTER = 151;
    public static final int FIREBASE_GOOGLE_REGISTER = 161;

//    RESULT
    public static final int ERROR_UNKNOWN = 500;
    public static final int ERROR_WRONG_PASS = 501;
    public static final int ERROR_NOT_REGISTERED = 502;
    public static final int ERROR_EMAIL_ALREADY_REGISTERED = 503;
    public static final int IS_SUCCESS = 200;
}
