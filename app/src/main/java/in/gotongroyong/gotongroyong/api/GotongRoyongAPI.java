package in.gotongroyong.gotongroyong.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseUser;

import in.gotongroyong.gotongroyong.ResponseActivity;
import in.gotongroyong.gotongroyong.ResultActivity;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.AdsClickBody;
import in.gotongroyong.gotongroyong.data.body.CampaignDetailBody;
import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.body.EmailRegisterBody;
import in.gotongroyong.gotongroyong.data.body.FacebookLoginBody;
import in.gotongroyong.gotongroyong.data.body.FacebookRegisterBody;
import in.gotongroyong.gotongroyong.data.body.GenerateAdsBody;
import in.gotongroyong.gotongroyong.data.body.GoogleLoginBody;
import in.gotongroyong.gotongroyong.data.body.GoogleRegisterBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.AdsResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignDetailResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.CampaignListResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.GenerateAdsResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.HeroResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.RegisterResponse;
import in.gotongroyong.gotongroyong.entity.API;
import in.gotongroyong.gotongroyong.entity.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GotongRoyongAPI {

    private static GotongRoyongService service = new Retrofit.Builder()
            .baseUrl(API.GOTONG_ROYONG_DEV_URL)
//                .baseUrl(API.GOTONG_ROYONG_PROD_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GotongRoyongService.class);

    public static GotongRoyongService getService() {
        return service;
    }

    public static void emailLogin(final ResultActivity activity, EmailLoginBody body) {
        Call<BaseResponse<LoginResponse>> call = service.emailLogin(body);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    saveData(((Activity) activity).getApplicationContext(), response.body().getPayload());
                    activity.onActivityResult(API.AUTH_EMAIL_LOGIN, API.IS_SUCCESS);
                } else {
                    activity.onActivityResult(API.AUTH_EMAIL_LOGIN, API.ERROR_WRONG_PASS);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                activity.onActivityResult(API.AUTH_EMAIL_LOGIN, API.ERROR_UNKNOWN);
            }
        });
    }

    public static void emailRegister(final ResultActivity activity, EmailRegisterBody body) {
        Call<BaseResponse<RegisterResponse>> call = service.emailRegister(body);
        call.enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    activity.onActivityResult(API.AUTH_EMAIL_REGISTER, API.IS_SUCCESS);
                } else {
                    activity.onActivityResult(API.AUTH_EMAIL_REGISTER, API.ERROR_EMAIL_ALREADY_REGISTERED);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                activity.onActivityResult(API.AUTH_EMAIL_REGISTER, API.ERROR_UNKNOWN);
            }
        });
    }

    public static void facebookLogin(final ResultActivity activity, FacebookLoginBody body) {
        Call<BaseResponse<LoginResponse>> call = service.facebookLogin(body);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    saveData(((Activity) activity).getApplicationContext(), response.body().getPayload());
                    activity.onActivityResult(API.AUTH_FACEBOOK_LOGIN, API.IS_SUCCESS);
                } else {
                    activity.onActivityResult(API.AUTH_FACEBOOK_LOGIN, API.ERROR_NOT_REGISTERED);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                activity.onActivityResult(API.AUTH_FACEBOOK_LOGIN, API.ERROR_UNKNOWN);
            }
        });
    }

    public static void facebookRegister(final ResultActivity activity, FacebookRegisterBody body) {
        Call<BaseResponse<RegisterResponse>> call = service.facebookRegister(body);
        call.enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    activity.onActivityResult(API.AUTH_FACEBOOK_REGISTER, API.IS_SUCCESS);
                } else {
                    activity.onActivityResult(API.AUTH_FACEBOOK_REGISTER, API.ERROR_EMAIL_ALREADY_REGISTERED);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                activity.onActivityResult(API.AUTH_FACEBOOK_REGISTER, API.ERROR_UNKNOWN);
            }
        });
    }

    public static void googleLogin(final ResultActivity activity, GoogleLoginBody body) {
        Call<BaseResponse<LoginResponse>> call = service.googleLogin(body);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    saveData(((Activity) activity).getApplicationContext(), response.body().getPayload());
                    activity.onActivityResult(API.AUTH_GOOGLE_LOGIN, API.IS_SUCCESS);
                } else {
                    activity.onActivityResult(API.AUTH_GOOGLE_LOGIN, API.ERROR_EMAIL_ALREADY_REGISTERED);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                activity.onActivityResult(API.AUTH_GOOGLE_LOGIN, API.ERROR_UNKNOWN);
            }
        });
    }

    public static void googleRegister(final ResultActivity activity, GoogleRegisterBody body) {
        Call<BaseResponse<RegisterResponse>> call = service.googleRegister(body);
        call.enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    activity.onActivityResult(API.AUTH_GOOGLE_REGISTER, API.IS_SUCCESS);
                } else {
                    activity.onActivityResult(API.AUTH_GOOGLE_REGISTER, API.ERROR_EMAIL_ALREADY_REGISTERED);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                activity.onActivityResult(API.AUTH_GOOGLE_REGISTER, API.ERROR_UNKNOWN);
            }
        });
    }

    public static void listCampaign(final ResponseActivity<CampaignListResponse> activity, int page) {
        final int responseCode = (page == 1)? API.CAMPAIGN_LIST_INIT : API.CAMPAING_LIST_UPDATE;
        Call<BaseResponse<CampaignListResponse>> call = service.listCampaign(page);
        call.enqueue(new Callback<BaseResponse<CampaignListResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CampaignListResponse>> call, Response<BaseResponse<CampaignListResponse>> response) {
                if (response.isSuccessful()) {
                    CampaignListResponse campaignList = response.body().getPayload();
                    activity.onActivityResponse(responseCode, API.IS_SUCCESS, campaignList);
                } else {
                    activity.onActivityResponse(responseCode, API.ERROR_UNKNOWN, null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CampaignListResponse>> call, Throwable t) {
                activity.onActivityResponse(responseCode, API.ERROR_NO_CONNECTION, null);
            }
        });
    }

    public static void getCampaign(final ResponseActivity<CampaignDetailResponse> activity, CampaignDetailBody body) {
        Call<BaseResponse<CampaignDetailResponse>> call = service.getCampaign(body);
        call.enqueue(new Callback<BaseResponse<CampaignDetailResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CampaignDetailResponse>> call, Response<BaseResponse<CampaignDetailResponse>> response) {
                if (response.isSuccessful()) {
                    CampaignDetailResponse detailResponse = response.body().getPayload();
                    activity.onActivityResponse(API.CAMPAIGN_DETAIL, API.IS_SUCCESS, detailResponse);
                } else {
                    activity.onActivityResponse(API.CAMPAIGN_DETAIL, API.ERROR_UNKNOWN, null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CampaignDetailResponse>> call, Throwable t) {
                activity.onActivityResponse(API.CAMPAIGN_DETAIL, API.ERROR_NO_CONNECTION, null);
            }
        });
    }

    public static void generateAds(final ResponseActivity<GenerateAdsResponse> activity, String api_token, GenerateAdsBody body) {
        Call<BaseResponse<GenerateAdsResponse>> call = service.generateAds(api_token, body);
        call.enqueue(new Callback<BaseResponse<GenerateAdsResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<GenerateAdsResponse>> call, Response<BaseResponse<GenerateAdsResponse>> response) {
                if (response.isSuccessful()) {
                    GenerateAdsResponse adsResponse = response.body().getPayload();
                    activity.onActivityResponse(API.ADS_GENERATE, API.IS_SUCCESS, adsResponse);
                } else {
                    activity.onActivityResponse(API.ADS_GENERATE, API.ERROR_UNKNOWN, null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<GenerateAdsResponse>> call, Throwable t) {
                activity.onActivityResponse(API.ADS_GENERATE, API.ERROR_NO_CONNECTION, null);
            }
        });
    }

    public static void clearData(Context context) {
        SharedPreferences userData = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE);
        userData.edit().clear().apply();
    }

    public static void saveData(Context context, LoginResponse response) {
        FirebaseUser loggedUser = FirebaseAPI.getLoggedUser();
        String id = loggedUser.getUid();
        String name = loggedUser.getDisplayName();

        HeroResponse hero = response.getDataPahlawan();
        int totalDonation = hero.getCountDonations();
        int totalShare = hero.getCountShares();
        int equivalent = hero.getCountDonations() * 100;

        SharedPreferences.Editor editor = context.getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE).edit();
        editor.putString(Preferences.USER_ID, id);
        editor.putString(Preferences.USER_NAME, name);
        editor.putInt(Preferences.USER_TOTAL_DONATION, totalDonation);
        editor.putInt(Preferences.USER_TOTAL_SHARE, totalShare);
        editor.putInt(Preferences.USER_EQUIVALENT, equivalent);
        editor.apply();
    }
}
