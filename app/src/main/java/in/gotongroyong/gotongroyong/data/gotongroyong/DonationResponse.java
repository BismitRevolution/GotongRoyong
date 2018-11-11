package in.gotongroyong.gotongroyong.data.gotongroyong;

public class DonationResponse {

    private int id;
    private int id_campaign;
    private int id_user;
    private int id_ads;
    private String device;
    private int click_target_url;
    private String created_at;
    private String updated_at;

    public int getIdAds() {
        return this.id_ads;
    }

    public int getCampaignId() {
        return this.id_campaign;
    }

    public int getUserId() {
        return this.id_user;
    }
}
