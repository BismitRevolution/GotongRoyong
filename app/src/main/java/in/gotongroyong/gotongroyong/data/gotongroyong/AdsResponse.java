package in.gotongroyong.gotongroyong.data.gotongroyong;

public class AdsResponse {

    private int id;
    private int id_advertiser;
    private String advertiser_name;
    private String advertiser_desc;
    private String advertiser_no_hp;
    private String advertiser_logo;
    private String advertiser_email;
    private String target_url;
    private String content_url;
    private int ads_category;
    private String description;
    private String bg_img_url;
    private String title_content;
    private String logo_url;
    private int flag_active;
    private int duration;
    private String created_at;

    public String getAdvName() {
        return this.advertiser_name;
    }

    public int getAdsCategory() {
        return this.ads_category;
    }

    public String getContentUrl() {
        return this.content_url;
    }

    public String getWebsiteUrl() {
        return this.target_url;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getAdvLogo() {
        return this.advertiser_logo;
    }
}
