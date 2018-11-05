package in.gotongroyong.gotongroyong.api;

import in.gotongroyong.gotongroyong.entity.API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GotongRoyongAPI {

    private GotongRoyongService service;

    public GotongRoyongAPI() {
        this.service = new Retrofit.Builder()
                .baseUrl(API.GOTONG_ROYONG_DEV_URL)
//                .baseUrl(API.GOTONG_ROYONG_PROD_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GotongRoyongService.class);
    }

    public GotongRoyongService getService() {
        return this.service;
    }
}
