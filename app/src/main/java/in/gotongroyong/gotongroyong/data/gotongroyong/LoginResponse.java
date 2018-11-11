package in.gotongroyong.gotongroyong.data.gotongroyong;

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
    private UserDataResponse data_pahlawan;

    public int getId() {
        return this.id;
    }

    public String getApiToken() {
        return this.api_token;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getBirthplace() {
        return this.birthplace;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public String getGender() {
        return this.gender;
    }

    public UserDataResponse getDataPahlawan() {
        return this.data_pahlawan;
    }
}
