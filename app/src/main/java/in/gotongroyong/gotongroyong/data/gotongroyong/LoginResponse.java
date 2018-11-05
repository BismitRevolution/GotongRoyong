package in.gotongroyong.gotongroyong.data.gotongroyong;

public class LoginResponse {

    private int id;
    private String username;
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
    private HeroResponse data_pahlawan;

    public int getId() {
        return this.id;
    }

    public String getFullname() {
        return this.fullname;
    }
}
