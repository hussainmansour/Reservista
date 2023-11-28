package Reservista.example.Backend;

public enum StatusCode {
    SUCCESS(200),
    NOT_FOUND(404),
    SERVER_ERROR(500),
    CREDENTIAL_ERROR(1),
    GOOGLERESPOND(403),
    INVALID_ARGUMENT(400);


    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
