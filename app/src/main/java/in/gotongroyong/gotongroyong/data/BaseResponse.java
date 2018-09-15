package in.gotongroyong.gotongroyong.data;

public class BaseResponse<T> {

    private String status;
    private String message;
    private T payload;

    public BaseResponse(String status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }
}
