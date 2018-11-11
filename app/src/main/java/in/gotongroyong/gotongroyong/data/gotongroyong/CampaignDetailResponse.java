package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.util.List;

import in.gotongroyong.gotongroyong.entity.API;

public class CampaignDetailResponse {

    private int id;
    private String title;
    private int id_user;
    private String campaigner_username;
    private String campaigner_fullname;
    private String campaigner_image_profile;
    private String description;
    private int count_donations;
    private int count_users;
    private int count_shares;
    private int target_donation;
    private String deadline;
    private int sisa_hari;
    private int complete_sts;
    private int flag_active;
    private String campaign_link;
    private String created_at;
    private String updated_at;
    private int created_by;
    private int updated_by;
    private List<ImageResponse> list_images;

    public int getCampaignId() {
        return this.id;
    }

    public String getCampaignerFullname() {
        return this.campaigner_fullname;
    }

    public String getCampaignerImgUrl() {
        return API.getBaseApiUrl() + this.campaigner_image_profile;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
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

