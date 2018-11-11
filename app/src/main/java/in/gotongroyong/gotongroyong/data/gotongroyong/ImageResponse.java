package in.gotongroyong.gotongroyong.data.gotongroyong;

import in.gotongroyong.gotongroyong.entity.API;

public class ImageResponse {

    private int id;
    private String img_url;

    public String getImageUrl() {
        return API.getBaseApiUrl() + this.img_url;
    }
}
