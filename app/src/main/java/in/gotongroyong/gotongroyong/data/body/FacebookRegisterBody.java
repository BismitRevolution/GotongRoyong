package in.gotongroyong.gotongroyong.data.body;

public class FacebookRegisterBody {

    private String email;
    private String id_fb;
    private String fullname;
    private String birthdate;
    private String birthplace;
    private String gender;
    private String image_profile;

    public FacebookRegisterBody(String email, String id_fb, String fullname, String birthdate, String birthplace, String gender, String image_profile) {
        this.email = email;
        this.id_fb = id_fb;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.gender = gender;
        this.image_profile = image_profile;
    }
}
