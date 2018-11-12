package in.gotongroyong.gotongroyong.api;

import in.gotongroyong.gotongroyong.data.body.AdsClickBody;
import in.gotongroyong.gotongroyong.data.body.CampaignDetailBody;
import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.EmailRegisterBody;
import in.gotongroyong.gotongroyong.data.body.FacebookLoginBody;
import in.gotongroyong.gotongroyong.data.body.FacebookRegisterBody;
import in.gotongroyong.gotongroyong.data.body.GenerateAdsBody;
import in.gotongroyong.gotongroyong.data.body.GoogleLoginBody;
import in.gotongroyong.gotongroyong.data.body.GoogleRegisterBody;
import in.gotongroyong.gotongroyong.data.body.ShareBody;
import in.gotongroyong.gotongroyong.data.body.UpdateProfileBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignDetailResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignListResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.GenerateAdsResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.HeroListResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @POST("/api/campaign/campaign-list/paginate")
    Call<BaseResponse<CampaignListResponse>> listCampaign(@Query("page") int page);

    @POST("/api/campaign/campaign-list/detail")
    Call<BaseResponse<CampaignDetailResponse>> getCampaign(@Body CampaignDetailBody body);

    @POST("/api/users/pahlawan/paginate")
    Call<BaseResponse<HeroListResponse>> listHero(@Query("page") int page);

    @POST("/api/auth/user/self-detail")
    Call<BaseResponse<LoginResponse>> getUserData(@Header("Authorization") String api_token);

    @POST("/api/auth/user/update")
    Call<BaseResponse<String>> updateProfile(@Header("Authorization") String api_token, @Body UpdateProfileBody body);

    @POST("/api/donates/campaign-ads/create")
    Call<BaseResponse<GenerateAdsResponse>> generateAds(@Header("Authorization") String api_token, @Body GenerateAdsBody body);

    @POST("/api/donates/campaign-ads/share-success")
    Call<BaseResponse<String>> share(@Body ShareBody body);

    @POST("/api/donates/campaign-ads/target-url")
    Call<BaseResponse<String>> adsClick(@Body AdsClickBody body);
}
