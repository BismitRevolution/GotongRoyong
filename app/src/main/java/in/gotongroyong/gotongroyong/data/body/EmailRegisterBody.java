package in.gotongroyong.gotongroyong.data.body;

public class EmailRegisterBody {

    private String email;
    private String password;
    private String password_confirmation;
    private String fullname;

    public EmailRegisterBody(String email, String password, String password_confirmation, String fullname) {
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.fullname = fullname;
    }
}
