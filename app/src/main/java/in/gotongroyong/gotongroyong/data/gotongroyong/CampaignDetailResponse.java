package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.util.List;

public class CampaignDetailResponse {

    private int id;
    private String title;
    private int id_user;
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

    public String getTitle() {
        return this.title;
    }

    public List<ImageResponse> getImageList() {
        return this.list_images;
    }
}

