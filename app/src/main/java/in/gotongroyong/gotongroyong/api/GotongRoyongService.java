package in.gotongroyong.gotongroyong.api;

import java.util.List;

import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.CampaignData;
import in.gotongroyong.gotongroyong.data.DonationData;
import in.gotongroyong.gotongroyong.data.HeroData;
import in.gotongroyong.gotongroyong.data.TestData;
import in.gotongroyong.gotongroyong.data.body.EmailRegisterBody;
import in.gotongroyong.gotongroyong.data.body.FacebookLoginBody;
import in.gotongroyong.gotongroyong.data.body.FacebookRegisterBody;
import in.gotongroyong.gotongroyong.data.body.GoogleLoginBody;
import in.gotongroyong.gotongroyong.data.body.GoogleRegisterBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GotongRoyongService {

    @POST("/api/auth/login")
    Call<BaseResponse<LoginResponse>> emailLogin(@Body EmailLoginBody body);

    @POST("/api/auth/register")
    Call<BaseResponse<RegisterResponse>> emailRegister(@Body EmailRegisterBody body);

    @POST("/api/auth/login/fb")
    Call<BaseResponse<LoginResponse>> facebookLogin(@Body FacebookLoginBody body);

    @POST("/api/auth/register/fb")
    Call<BaseResponse<RegisterResponse>> facebookRegister(@Body FacebookRegisterBody body);

    @POST("/api/auth/login/google")
    Call<BaseResponse<LoginResponse>> googleLogin(@Body GoogleLoginBody body);

    @POST("/api/auth/register/google")
    Call<BaseResponse<RegisterResponse>> googleRegister(@Body GoogleRegisterBody body);

    @GET("/test")
    Call<TestData> test();

    @GET("/campaign/pagination/{page}")
    Call<BaseResponse<List<CampaignData>>> listCampaign(@Path("page") int page);

    @GET("/campaign/{id}")
    Call<BaseResponse<CampaignData>> getCampaign(@Path("id") String id);

    @GET("/hero/pagination/{page}")
    Call<BaseResponse<List<HeroData>>> listHero(@Path("page") int page);

    @GET("/hero/{id}")
    Call<BaseResponse<HeroData>> getHero(@Path("id") String id);

    @GET("/donation/pagination/{page}")
    Call<List<DonationData>> listDonation(@Path("page") int page);

}
