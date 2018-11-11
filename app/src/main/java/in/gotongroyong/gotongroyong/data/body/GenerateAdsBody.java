package in.gotongroyong.gotongroyong.data.body;

public class GenerateAdsBody {

    private int id_campaign;
    private String device;

    public GenerateAdsBody(int id_campaign) {
        this.id_campaign = id_campaign;
        this.device = "android";
    }
}
