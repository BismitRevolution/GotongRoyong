package in.gotongroyong.gotongroyong.data;

public class HeroData {

    private String id;
    private String name;
    private String birth_place;
    private String birth_date;
    private String city;
    private String email;
    private int gender;
    private String img_url;
    private String facebook_url;
    private String twitter_url;
    private String instagram_url;
    private boolean verified;
    private int total_donation;
    private int total_campaign;
    private int total_share;

    public HeroData(String id, String name, String birth_place, String birth_date, String city, String email, int gender, String img_url, String facebook_url, String twitter_url, String instagram_url, boolean verified, int total_donation, int total_campaign, int total_share) {
        this.id = id;
        this.name = name;
        this.birth_place = birth_place;
        this.birth_date = birth_date;
        this.city = city;
        this.email = email;
        this.gender = gender;
        this.img_url = img_url;
        this.facebook_url = facebook_url;
        this.twitter_url = twitter_url;
        this.instagram_url = instagram_url;
        this.verified = verified;
        this.total_donation =total_donation;
        this.total_campaign = total_campaign;
        this.total_share = total_share;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthPlace() {
        return birth_place;
    }

    public String getBirthDate() {
        return birth_date;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public int getGender() {
        return gender;
    }

    public String getImgUrl() {
        return img_url;
    }

    public String getFacebookUrl() {
        return facebook_url;
    }

    public String getTwitterUrl() {
        return twitter_url;
    }

    public String getInstagramUrl() {
        return instagram_url;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getTotalDonation() {
        return total_donation;
    }

    public int getTotalCampaign() {
        return total_campaign;
    }

    public int getTotalShare() {
        return total_share;
    }
}
