package in.gotongroyong.gotongroyong.data.gotongroyong;

public class RegisterResponse {

    private String email;
    private String fullname;
    private String username;
    private int role;
    private int flag_active;
    private String updated_at;
    private String created_at;
    private int id;
    private String api_token;
    private HeroResponse data_pahlawan;

    public String getEmail() {
        return this.email;
    }

    public String getFullname() {
        return this.fullname;
    }
}
