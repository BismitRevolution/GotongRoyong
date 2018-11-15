package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.util.List;

import in.gotongroyong.gotongroyong.entity.API;

public class CampaignResponse {

    private int item_id;
    private int id_campaign;
    private String title;
    private String campaigner_user;
    private String image_profile;
    private int flag_verified_user;
    private int count_donations;
    private int count_users;
    private int count_shares;
    private int target_donation;
    private int sisa_hari;
    private int complete_sts;
    private String campaign_link;
    private String created_at;
    private List<ImageResponse> list_images;

    public int getCampaignId() {
        return this.id_campaign;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCampaignUser() {
        return this.campaigner_user;
    }

    public String getImageProfile() {
        return API.getBaseApiUrl() + "/" + this.image_profile;
    }

    public int getCountDonations() {
        return this.count_donations;
    }

    public int getCountUsers() {
        return this.count_users;
    }

    public int getCountShares() {
        return this.count_shares;
    }

    public int getTargetDonation() {
        return this.target_donation;
    }

    public int getDayLeft() {
        return this.sisa_hari;
    }

    public List<ImageResponse> getImageList() {
        return this.list_images;
    }
}
