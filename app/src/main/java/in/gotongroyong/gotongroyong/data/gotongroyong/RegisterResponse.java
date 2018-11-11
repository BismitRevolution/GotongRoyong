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
    private UserDataResponse data_pahlawan;

    public String getEmail() {
        return this.email;
    }

    public String getApiToken() {
        return this.api_token;
    }

    public String getFullname() {
        return this.fullname;
    }

    public UserDataResponse getDataPahlawan() {
        return this.data_pahlawan;
    }
}
