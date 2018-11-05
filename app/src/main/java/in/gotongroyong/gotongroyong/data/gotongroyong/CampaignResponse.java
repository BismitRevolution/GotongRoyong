package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.util.List;

public class CampaignResponse {

    private int item_id;
    private int id_campaign;
    private String title;
    private String campaign_user;
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

    public String getTitle() {
        return this.title;
    }

    public List<ImageResponse> getImageList() {
        return this.list_images;
    }
}
