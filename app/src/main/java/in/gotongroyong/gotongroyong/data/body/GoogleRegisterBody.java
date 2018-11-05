package in.gotongroyong.gotongroyong.data.body;

public class GoogleRegisterBody {

    private String email;
    private String id_google;
    private String fullname;
    private String birthdate;
    private String birthplace;
    private String gender;
    private String image_profile;

    public GoogleRegisterBody(String email, String id_google, String fullname, String birthdate, String birthplace, String gender, String image_profile) {
        this.email = email;
        this.id_google = id_google;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.gender = gender;
        this.image_profile = image_profile;
    }
}
