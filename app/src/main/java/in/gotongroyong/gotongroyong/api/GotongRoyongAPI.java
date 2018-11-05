package in.gotongroyong.gotongroyong.api;

import in.gotongroyong.gotongroyong.ResultActivity;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.EmailLoginBody;
import in.gotongroyong.gotongroyong.data.body.EmailRegisterBody;
import in.gotongroyong.gotongroyong.data.body.FacebookLoginBody;
import in.gotongroyong.gotongroyong.data.body.FacebookRegisterBody;
import in.gotongroyong.gotongroyong.data.body.GoogleLoginBody;
import in.gotongroyong.gotongroyong.data.body.GoogleRegisterBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.RegisterResponse;
import in.gotongroyong.gotongroyong.entity.API;
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

    public GotongRoyongService getService() {
        return this.service;
    }

    public static void emailLogin(final ResultActivity activity, EmailLoginBody body) {
        Call<BaseResponse<LoginResponse>> call = service.emailLogin(body);
        call.enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
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
}
