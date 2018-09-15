package in.gotongroyong.gotongroyong.data;

public class DonationData {

    private String id;
    private String title;
    private String img_url;
    private String description;
    private int total_hero;
    private int total_donation;
    private int target_donation;
    private int day_left;
    private String client_id;
    private String client_name;
    private String client_img;
    private int participation;

    public DonationData(String id, String title, String img_url, String description, int total_hero, int total_donation, int target_donation, int day_left, String client_id, String client_name, String client_img, int participation) {
        this.id = id;
        this.title = title;
        this.img_url = img_url;
        this.description = description;
        this.total_hero = total_hero;
        this.total_donation = total_donation;
        this.target_donation = target_donation;
        this.day_left = day_left;
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_img = client_img;
        this.participation = participation;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return id;
    }

    public String getImgUrl() {
        return img_url;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalHero() {
        return total_hero;
    }

    public int getTotalDonation() {
        return total_donation;
    }

    public int getTargetDonation() {
        return target_donation;
    }

    public int getDayLeft() {
        return day_left;
    }

    public String getClientId() {
        return client_id;
    }

    public String getClientName() {
        return client_name;
    }

    public String getClientImg() {
        return client_img;
    }

    public int getParticipation() {
        return participation;
    }
}
