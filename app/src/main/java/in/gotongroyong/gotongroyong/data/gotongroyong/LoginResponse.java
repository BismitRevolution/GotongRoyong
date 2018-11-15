package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class LoginResponse {

    private int id;
    private String username;
    private String email;
    private String api_token;
    private String fullname;
    private String birthdate;
    private String birthplace;
    private String gender;
    private int role;
    private int flag_active;
    private String created_at;
    private String updated_at;
    private String image_profile;
    private String image_profile_location;
    private String id_fb;
    private String id_google;
    private String bg_image_profile;
    private int flag_email_verified;
    private String email_token;
    private String no_hp;
    private UserDataResponse data_pahlawan;

    public int getId() {
        return this.id;
    }

    public String getApiToken() {
        return this.api_token;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getBirthplace() {
        return this.birthplace;
    }

    public String getBirthdate() {
        if (this.birthdate != null) {
            SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat formFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            String result = this.birthdate;

            try {
                Date date = apiFormat.parse(this.birthdate);
                result = formFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return result;
        } else {
            return null;
        }
    }

    public String getGender() {
        return this.gender;
    }

    public String getPhoneNumber() {
        return this.no_hp;
    }

    public UserDataResponse getDataPahlawan() {
        return this.data_pahlawan;
    }
}
