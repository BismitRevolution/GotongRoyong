package in.gotongroyong.gotongroyong;

public interface ResponseActivity<T> {
    void onActivityResponse(int responseCode, int resultCode, T response);
}
