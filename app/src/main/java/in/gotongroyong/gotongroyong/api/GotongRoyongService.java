package in.gotongroyong.gotongroyong.api;

import java.util.List;

import in.gotongroyong.gotongroyong.data.TestData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GotongRoyongService {

    @GET("/repo/{user}/list")
    Call<List> listRepos(@Path("user") String user);

    @GET("/test")
    Call<TestData> test();
}
