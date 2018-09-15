package in.gotongroyong.gotongroyong.api;

import java.util.List;

import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.CampaignData;
import in.gotongroyong.gotongroyong.data.DonationData;
import in.gotongroyong.gotongroyong.data.HeroData;
import in.gotongroyong.gotongroyong.data.TestData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GotongRoyongService {

    @GET("/repo/{user}/list")
    Call<List> listRepos(@Path("user") String user);

    @GET("/test")
    Call<TestData> test();

    @GET("/campaign/pagination/{page}")
    Call<BaseResponse<List<CampaignData>>> listCampaign(@Path("page") int page);

    @GET("/hero/pagination/{page}")
    Call<BaseResponse<List<HeroData>>> listHero(@Path("page") int page);

    @GET("/donation/pagination/{page}")
    Call<List<DonationData>> listDonation(@Path("page") int page);

}
