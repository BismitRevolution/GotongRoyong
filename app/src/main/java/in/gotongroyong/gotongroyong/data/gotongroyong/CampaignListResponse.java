package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.util.List;

public class CampaignListResponse {

    private int current_page;
    private List<CampaignResponse> data;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private int to;
    private int total;

    public int getCurrentPage() {
        return this.current_page;
    }

    public List<CampaignResponse> getData() {
        return this.data;
    }
}
