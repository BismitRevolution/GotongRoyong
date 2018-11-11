package in.gotongroyong.gotongroyong.data;

public class BaseResponse<T> {

    private String status;
    private boolean success;
    private String message;
    private T data;

    public BaseResponse(String status, boolean success, String message, T payload) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = payload;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return data;
    }
}
