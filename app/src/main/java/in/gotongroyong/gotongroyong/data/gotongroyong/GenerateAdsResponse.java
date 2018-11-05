package in.gotongroyong.gotongroyong.data.gotongroyong;

public class GenerateAdsResponse {

    private DonationResponse donation_data;
    private AdsResponse ads_data;

    public DonationResponse getDonationData() {
        return this.donation_data;
    }

    public AdsResponse getAdsData() {
        return this.ads_data;
    }
}
