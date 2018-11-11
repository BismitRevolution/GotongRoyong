package in.gotongroyong.gotongroyong.data.gotongroyong;

public class HeroResponse {

    private int id_user;
    private String fullname;
    private int flag_verified;
    private String image_profile;
    private String fb_link;
    private String twitter_link;
    private String instagram_link;
    private int count_donations;
    private int count_campaign_owned;

    public int getIdUser() {
        return this.id_user;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getImgUrl() {
        return this.image_profile;
    }

    public String getFacebookUrl() {
        return this.fb_link;
    }

    public String getTwitterUrl() {
        return this.twitter_link;
    }

    public String getInstagramUrl() {
        return this.instagram_link;
    }

    public int getCountDonation() {
        return this.count_donations;
    }

    public int getCountCampaign() {
        return this.count_campaign_owned;
    }
}
