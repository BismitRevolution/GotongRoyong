package in.gotongroyong.gotongroyong.data.gotongroyong;

public class HeroResponse {

    private int id;
    private int count_shares;
    private int count_campaigns;
    private int count_donations;
    private String about_me;
    private String my_url;
    private String instagram_link;
    private String twitter_link;
    private String fb_link;
    private int flag_verified;

    public int getId() {
        return this.id;
    }

    public int getCountShares() {
        return this.count_shares;
    }

    public int getCountCampaigns() {
        return this.count_campaigns;
    }

    public int getCountDonations() {
        return this.count_donations;
    }
}
