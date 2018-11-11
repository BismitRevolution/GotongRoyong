package in.gotongroyong.gotongroyong.entity;

public class API {
    public static final String MODE_LOCAL = "LOCAL";
    public static final String MODE_DEV = "DEV";
    public static final String MODE_PROD = "PROD";

    public static String APP_MODE = MODE_PROD;

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

//    CAMPAIGN
    public static final int CAMPAIGN_LIST_INIT = 600;
    public static final int CAMPAING_LIST_UPDATE = 601;
    public static final int CAMPAIGN_DETAIL = 602;
    public static final int CAMPAIGN_SHARE = 603;

//    HERO
    public static final int HERO_LIST_INIT = 700;
    public static final int HERO_LIST_UPDATE = 701;
    public static final int HERO_USER_DATA = 702;

//    ADS
    public static final int ADS_GENERATE = 800;
    public static final int ADS_CLICK = 801;

//    RESULT
    public static final int ERROR_UNKNOWN = 500;
    public static final int ERROR_WRONG_PASS = 501;
    public static final int ERROR_NOT_REGISTERED = 502;
    public static final int ERROR_EMAIL_ALREADY_REGISTERED = 503;

    public static final int ERROR_NO_CONNECTION = 505;

    public static final int IS_SUCCESS = 200;

    public static String getBaseApiUrl() {
        switch (APP_MODE) {
            case MODE_DEV:
                return GOTONG_ROYONG_DEV_URL;
            case MODE_LOCAL:
                return GOTONG_ROYONG_LOCAL_URL;
            case MODE_PROD:
                return GOTONG_ROYONG_PROD_URL;
            default:
                return TEST_URL;
        }
    }
}
