package in.gotongroyong.gotongroyong.data.gotongroyong;

import java.util.List;

public class HeroListResponse {

    private int current_page;
    private List<HeroResponse> data;
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

    public String getNextPageUrl() {
        return this.next_page_url;
    }

    public List<HeroResponse> getData() {
        return this.data;
    }


}
