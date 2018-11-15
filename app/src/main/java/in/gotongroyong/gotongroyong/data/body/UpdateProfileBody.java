package in.gotongroyong.gotongroyong.data.body;

public class UpdateProfileBody {

    private String username;
    private String email;
    private String birthdate;
    private String birthplace;
    private String gender;
    private String fullname;
//    private String about_me;
//    private String my_url;
    private String password;
    private String no_hp;
    private String fb_link;
    private String twitter_link;
    private String instagram_link;
//    private String flag_verified;

    public UpdateProfileBody(String username, String email, String birthdate, String birthplace, String gender, String fullname, String password, String no_hp, String fb_link, String twitter_link, String instagram_link) {
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.gender = gender;
        this.fullname = fullname;
        this.password = password;
        this.no_hp = no_hp;
        this.fb_link = fb_link;
        this.twitter_link = twitter_link;
        this.instagram_link = instagram_link;
    }
}
